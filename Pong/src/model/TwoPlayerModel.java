package model;

import java.awt.Dimension;

public class TwoPlayerModel extends AbstractModel {

    private int rightPlayerScore, leftPlayerScore;
    private int gameUntil = 12;

    public TwoPlayerModel(Dimension gameDimensions, Ball ball, Paddle paddle1, Paddle paddle2) {
        super(gameDimensions, ball, paddle1, paddle2);
        paddle2.setRightPaddle(true); 
    }
    
    public void setGameUntilScore(int score){
        gameUntil = score;
    }


    @Override
    protected void onHitLeftWall() {       
        if (getRightPlayerScore() == gameUntil - 1) {
            gameOver();
        }
        else{
            incrementRightScore();
            resetBall(paddles.get(0));
        }       
    }

    @Override
    protected void onHitRightWall() {
         if (getLeftPlayerScore() == gameUntil - 1) {
            gameOver();
        }
        else{
            incrementLeftScore();
            resetBall(paddles.get(1));
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

    private void resetBall(Paddle loser) {
        double startingX = (loser.isRightPaddle()) ?  gameDimensions.width/3 : (gameDimensions.width - gameDimensions.width/3);
        ball.setCenter(startingX , (gameDimensions.height/2));
        resetBallSpeed();
    }

}