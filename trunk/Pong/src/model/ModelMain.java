package model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yosef Friedman & Yosef Lejtman
 */
public abstract class ModelMain {

    protected final Ball ball;

    List<Paddle> paddles = new ArrayList<>();

    protected final Dimension gameDimensions;

    protected int scoreIncrementAmount = 1;

    protected boolean isGameOver;

    private Point oldCenter;
    private double speedFactor = 1;

    public ModelMain(Dimension gameDimentions, Ball ball, Paddle... paddles) {
        this.ball = ball;
        this.paddles.addAll(Arrays.asList(paddles));
        this.gameDimensions = new Dimension(gameDimentions);
    }
    
    public void setScoreIncrementAmount(int newAmount){
        scoreIncrementAmount = newAmount;
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
        oldCenter = ball.getCenter();
        ball.updatePosition(speedFactor);
        resolveBallCollisions();
    }
    
    protected void speedUpBallBy(double amount){
        speedFactor += amount;
    }
    protected void resetBallSpeed(){
        speedFactor = 1;
    }
    

    private void updatePaddlePosition() {
        for (Paddle paddle : paddles) {
                if (paddle.isMovingUp() && paddle.getBody().y > 0) {
                    paddle.moveUp();
                }
                if (paddle.isMovingDown() && paddle.getBody().y + paddle.getHeight() < gameDimensions.height) {
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
            System.out.println("BALL: " + ball.getCenter());
            System.out.println("PADDLE: " + paddle.getBody());
            onHitPaddle(paddle);
        });
    }


    protected void onHitPaddle(Paddle paddle){
        ball.setCenter(oldCenter.x, oldCenter.y);
        
        Rectangle paddleBody = paddle.getBody();
        
        if(isHittingOffSideOfPaddle(paddleBody)){
            ball.setDeltaY(-ball.getDeltaY());
            System.out.println("TOP / BOTTOM");
        }
        else{
            ball.setDeltaX( -ball.getDeltaX()); //negative
        }
    }

    private boolean isHittingOffSideOfPaddle(Rectangle paddleBody) {
        return ball.getCenter().y < paddleBody.y || 
                ball.getCenter().y > paddleBody.y + paddleBody.height;
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
        if ((paddle.isRightPaddle() && ball.getDeltaX() > 0) || (!paddle.isRightPaddle() && ball.getDeltaX() < 0)) {
            Ellipse2D ballBody = new Ellipse2D.Float(
                    ball.getCenter().x, ball.getCenter().y, 
                    ball.getRadius(), ball.getRadius());
            
            return ballBody.intersects(paddle.getBody());
        } else {
            return false;
        }
    }

}
