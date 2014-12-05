package model;

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

    protected final int width, height;

    protected final int scoreIncrementAmount;

    protected boolean isGameOver;

    protected Point oldCenter;

    public ModelMain(int width, int height, int scoreIncrementAmount, Ball ball, Paddle... paddles) {
        this.width = width;
        this.height = height;
        this.ball = ball;
        this.scoreIncrementAmount = scoreIncrementAmount;
        this.paddles.addAll(Arrays.asList(paddles));
    }

    public Ball getBall() {
        return new Ball(ball.getCenter(), ball.getRadius(), ball.getDeltaX(), ball.getDeltaY());
    }

    public List<Paddle> getPaddles() {
        return new ArrayList<>(paddles);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
        ball.updatePosition();
        resolveBallCollisions();
    }

    private void updatePaddlePosition() {
        for (Paddle paddle : paddles) {
                if (paddle.isMovingUp() && paddle.getBody().y > 0) {
                    paddle.moveUp();
                }
                if (paddle.isMovingDown() && paddle.getBody().y + paddle.getHeight() < height) {
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
        paddles.stream().filter((paddle) -> (isHittingPaddle(paddle))).forEach((_item) -> {
            System.out.println("BALL: " + ball.getCenter());
            System.out.println("PADDLE: " + _item.getBody());
            onHitPaddle(_item);
        });
    }
    
    protected void handleBallPaddleCollision(Ball ball, Paddle paddle){
        ball.setCenter(oldCenter);
        
        Rectangle paddleBody = paddle.getBody();
        
        if(ball.getCenter().y < paddleBody.y || 
                ball.getCenter().y > paddleBody.y + paddleBody.height){
            ball.setDeltaY(-ball.getDeltaY());
            System.out.println("TOP / BOTTOM");
        }
        else{
            ball.setDeltaX( -ball.getDeltaX()); //negative
        }
    }

    protected abstract void onHitPaddle(Paddle paddle);

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
                = ball.getCenter().y + ball.getRadius() >= height;
        return isHittingTopWall || isHittingBottomWall;
    }

    protected boolean isHittingRightWall() {
        return ball.getCenter().x + ball.getRadius() >= width;
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
