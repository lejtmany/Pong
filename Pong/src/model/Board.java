package model;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yosef Friedman & Yosef Lejtman
 */
public abstract class Board {

    protected final Ball ball;

    List<Paddle> paddles = new ArrayList<>();

    protected final int width, height;

    protected final int scoreIncrementAmount;

    protected boolean isGameOver;

    private Point oldCenter;

    public Board(int width, int height, int scoreIncrementAmount, Ball ball, Paddle... paddles) {
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
            if (paddle.isMovingUp() && paddleIsNotAgainstTop(paddle)) {
                paddle.moveUp();
            }
            if (paddle.isMovingDown() && paddleIsNotAgainstBottom(paddle)) {
                paddle.moveDown();
            }
        }
    }

    private static boolean paddleIsNotAgainstTop(Paddle paddle) {
        return paddle.getTop().y > 0;
    }

    private boolean paddleIsNotAgainstBottom(Paddle paddle) {
        return paddle.getTop().y + paddle.getLength() < height;
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
            onHitPaddle();
        });
    }

    protected abstract void onHitPaddle();

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
            Line2D.Float lineBetweenTwoCenters = new Line2D.Float(
                    oldCenter,
                    new Point(
                            ball.getCenter().x - ball.getRadius(),
                            ball.getCenter().y));
            Line2D.Float paddleLine = new Line2D.Float(
                    paddle.getTop(),
                    new Point(
                            paddle.getTop().x,
                            paddle.getTop().y + paddle.getLength()));
            return lineBetweenTwoCenters.intersectsLine(paddleLine);
        } else {
            return false;
        }
    }

}
