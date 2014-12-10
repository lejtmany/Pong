package model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class AbstractModel {

    protected final Ball ball;

    List<Paddle> paddles = new ArrayList<>();

    protected final Dimension gameDimensions;

    protected int scoreIncrementAmount = 1;

    protected boolean isGameOver;

    private Point oldCenterOfBall;
    private double ballSpeedFactor = 100.0/controller.BoardController.updateTimesPerSecond;
    private double defaultBallSpeedFactor = 100.0/controller.BoardController.updateTimesPerSecond;
    private final double ballSpeedUpFactor = 1.0/controller.BoardController.updateTimesPerSecond;


    public AbstractModel(Dimension gameDimentions, Ball ball, Paddle... paddles) {
        this.ball = ball;
        this.paddles.addAll(Arrays.asList(paddles));
        this.gameDimensions = new Dimension(gameDimentions);
    }
    
    public void setScoreIncrementAmount(int newAmount){
        scoreIncrementAmount = newAmount;
    }
    public void setDefaultBallSpeedFactor(double factor){
        defaultBallSpeedFactor = factor;
        ballSpeedFactor = defaultBallSpeedFactor;
    }
    public void setPaddleSpeed(double newSpeed){
        for(Paddle p : paddles){
            p.setSpeed(newSpeed);
        }
    }

    public Ball getBall() {
        return new Ball(ball.getCenter(), ball.getRadius());
    }

    public List<Paddle> getPaddles() {
        return new ArrayList<>(paddles);
    }
    
    public Dimension getGameDimensions(){
        return new Dimension(gameDimensions);
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public void updateBoard() {
        updateBallPosition();
        updatePaddlePosition();
    }

    private void updateBallPosition() {
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
    

    private void updatePaddlePosition() {
        for (Paddle paddle : paddles) {
                if (paddle.isMovingUp() && paddle.getY() > 0) {
                    paddle.moveUp();
                }
                if (paddle.isMovingDown() && paddle.getY() + paddle.getHeight() < gameDimensions.height) {
                    paddle.moveDown();
                }           
        }
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
        paddles.stream().filter((paddle) -> (isHittingPaddle(paddle))).forEach((paddle) -> {
            onHitPaddle(paddle);
        });
    }


    protected void onHitPaddle(Paddle paddle){
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

    protected boolean isHittingPaddle(Paddle paddle) {
        //check to make sure only hits paddle once
        if (ballIsMovingTowardsPaddle(paddle)) {
            int ballRadius =  ball.getRadius();
            Ellipse2D ballBody = new Ellipse2D.Float(
                    ball.getCenter().x - ballRadius, ball.getCenter().y - ballRadius, 
                    ballRadius * 2 , ballRadius * 2);
            
            return ballBody.intersects(paddle.getBody());
        } else {
            return false;
        }
    }

    private boolean ballIsMovingTowardsPaddle(Paddle paddle) {
        return (paddle.isRightPaddle() && ball.getDeltaX() > 0) 
                || (!paddle.isRightPaddle() && ball.getDeltaX() < 0);
    }

}
