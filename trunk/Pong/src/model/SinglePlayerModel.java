package model;

import java.awt.Dimension;


public class SinglePlayerModel extends ModelMain {

    private int score;

    public SinglePlayerModel(Dimension gameDimensions, Ball ball, Paddle paddle1) {
        super(gameDimensions, ball, paddle1);
    }

    @Override
    protected void onHitPaddle(Paddle paddle) {
        super.onHitPaddle(paddle);
        incrementScore();
    }

    @Override
    protected void onHitLeftWall() {
        gameOver();
    }

    @Override
    protected void onHitRightWall() {
        ball.setDeltaX(
                -ball.getDeltaX()); //negative
    }

    @Override
    protected void gameOver() {
        setGameOver(true);
    }

    private void incrementScore() {
        score += scoreIncrementAmount;
    }

    public int getScore() {
        return score;
    }

}
