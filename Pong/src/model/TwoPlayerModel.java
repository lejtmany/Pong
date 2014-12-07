/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author Miriam
 */
public class TwoPlayerModel extends ModelMain {

    private int rightPlayerScore, leftPlayerScore;
    private final double ballSpeedUp = .10;
    private int gameUntil = 12;

    public TwoPlayerModel(Dimension gameDimensions, Ball ball, Paddle paddle1, Paddle paddle2) {
        super(gameDimensions, ball, paddle1, paddle2);
        paddle2.setRightPaddle(true); 
    }
    
    public void setGameUntilScore(int score){
        gameUntil = score;
    }
    

    @Override
    protected void onHitPaddle(Paddle paddle) {
        super.onHitPaddle(paddle);
        increaseBallSpeed();
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
        Dimension bounds = super.getGameDimensions();
        ball.setCenter(new Point(bounds.width/2, bounds.height/2));
    }

}
