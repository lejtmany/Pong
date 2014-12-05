/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Miriam
 */
public class TwoPlayerGame extends ModelMain {

    private int rightPlayerScore, leftPlayerScore;
    private final double ballSpeedUp = .10;
    private final int gameUntil;

    public TwoPlayerGame(int width, int height, int scoreIncrementAmount, int gameUntil, Ball ball, Paddle paddle1, Paddle paddle2) {
        super(width, height, scoreIncrementAmount, ball, paddle1, paddle2);
        paddle2.setRightPaddle(true);
        this.gameUntil = gameUntil;
    }

    @Override
    protected void onHitPaddle(Paddle paddle) {
        handleBallPaddleCollision(ball, paddle);
        increaseBallSpeed();
        System.out.println("Ball delta x: " + ball.getDeltaX());
    }

    private void increaseBallSpeed() {
        double ballDeltaSpeed = ballSpeedUp / 2 + Math.abs(ball.getDeltaX());
        ball.setDeltaX(ballDeltaSpeed * ((ball.getDeltaX() < 0) ? -1 : 1));
        ball.setDeltaY(ballDeltaSpeed * ((ball.getDeltaY() < 0) ? -1 : 1));
    }

    @Override
    protected void onHitLeftWall() {       
        if (getRightPlayerScore() == gameUntil - 1) {
            gameOver();
        }
        else{
            incrementRightScore();
            resetBall();
        }       
    }

    @Override
    protected void onHitRightWall() {
         if (getLeftPlayerScore() == gameUntil - 1) {
            gameOver();
        }
        else{
            incrementLeftScore();
            resetBall();
        }       
    }

    @Override
    protected void gameOver() {
        setGameOver(true);
    }

    public int getRightPlayerScore() {
        return rightPlayerScore;
    }

    public int getLeftPlayerScore() {
        return leftPlayerScore;
    }

    private void incrementRightScore() {
        rightPlayerScore += scoreIncrementAmount;
    }

    private void incrementLeftScore() {
        leftPlayerScore += scoreIncrementAmount;
    }

    private void resetBall() {
        ball.setCenter(new Point(super.width/2, super.height/2));
    }

}
