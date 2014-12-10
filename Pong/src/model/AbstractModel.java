package model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import static controller.BoardController.MILLISECONDS_IN_SECOND;
import java.awt.Rectangle;

public abstract class AbstractModel {

    protected final Ball ball;

    protected final Dimension gameDimensions;

    protected int scoreIncrementAmount = 1;

    protected boolean isGameOver;

    private Point oldCenterOfBall;
    private double ballSpeedFactor = 100.0/MILLISECONDS_IN_SECOND;
    private double defaultBallSpeedFactor = 100.0/MILLISECONDS_IN_SECOND;
    private final double ballSpeedUpFactor = 1.0/MILLISECONDS_IN_SECOND;


    public AbstractModel(Dimension gameDimentions, Ball ball) {
        this.ball = ball;
        this.gameDimensions = new Dimension(gameDimentions);
    }
    
    public void setScoreIncrementAmount(int newAmount){
        scoreIncrementAmount = newAmount;
    }
    public void setDefaultBallSpeedFactor(double factor){
        defaultBallSpeedFactor = factor;
        ballSpeedFactor = defaultBallSpeedFactor;
    }
    public abstract void setPaddleSpeed(double plxPerSec);

    public Ellipse2D getBall() {
        double radius = ball.getRadius();
        Point center = ball.getCenter();
        return new Ellipse2D.Double(center.x - radius, center.y - radius,
                            radius * 2, radius * 2);
    }
    
    public abstract Rectangle[] getPaddles();
    
    public Dimension getGameDimensions(){
        return new Dimension(gameDimensions);
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public void updateBall() {
        oldCenterOfBall = ball.getCenter();
        ball.updatePosition(ballSpeedFactor);
        resolveBallCollisions();
    }
    
    protected void speedUpBallBy(double amount){
        ballSpeedFactor += amount;
        System.out.println("ballSpeedFactor = " + ballSpeedFactor);
    }
    protected void resetBallSpeed(){
        ballSpeedFactor = defaultBallSpeedFactor;
    }

    protected void resolveBallCollisions() {
        if (isHittingHorizontalWall()) {
            onHitHorizontalWall();
        }
        if (isHittingRightWall()) {
            onHitRightWall();
        }
        if (isHittingLeftWall()) {
            onHitLeftWall();
        }
        for(Rectangle paddle : getPaddles()){
            if(isHittingPaddle(paddle)){
                onHitPaddle();
            }
        }
    }
    
    protected boolean paddleCollidingWithWall(Paddle paddle, Direction dir){
        if(dir == Direction.UP && paddle.getY() <= 0)
        {
            return true;
        }
        if(dir == Direction.DOWN &&
                paddle.getY() + paddle.getHeight() >= gameDimensions.height)
        {
            return true;
        }
        return false;
    }


    protected void onHitPaddle(){
        ball.setCenter(oldCenterOfBall.x, oldCenterOfBall.y);
        speedUpBallBy(ballSpeedUpFactor);
        ball.setDeltaX( -ball.getDeltaX()); //negative
    }


    protected abstract void onHitLeftWall();

    protected void onHitHorizontalWall() {
        ball.setDeltaY(
                -ball.getDeltaY()); //negative
    }

    protected abstract void onHitRightWall();

    protected abstract void gameOver();

    protected boolean isHittingHorizontalWall() {
        boolean isHittingTopWall
                = ball.getCenter().y - ball.getRadius() <= 0;
        boolean isHittingBottomWall
                = ball.getCenter().y + ball.getRadius() >= gameDimensions.height;
        return isHittingTopWall || isHittingBottomWall;
    }

    protected boolean isHittingRightWall() {
        return ball.getCenter().x + ball.getRadius() >= gameDimensions.width;
    }

    protected boolean isHittingLeftWall() {
        return ball.getCenter().x - ball.getRadius() <= 0;
    }

    protected boolean isHittingPaddle(Rectangle paddle) {
        //check to make sure only hits paddle once
        if (ballIsMovingTowardsPaddle(paddle)) {
            int ballRadius =  ball.getRadius();
            Ellipse2D ballBody = new Ellipse2D.Float(
                    ball.getCenter().x - ballRadius, ball.getCenter().y - ballRadius, 
                    ballRadius * 2 , ballRadius * 2);
            
            return ballBody.intersects(paddle);
        } else {
            return false;
        }
    }

    private boolean ballIsMovingTowardsPaddle(Rectangle paddle) {
        int ballX = ball.getCenter().x;
        return (paddle.x > ballX && ball.getDeltaX() > 0) 
                || (paddle.x < ballX && ball.getDeltaX() < 0);
    }

}
