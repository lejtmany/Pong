package model;


public class SinglePlayerBoard extends Board {

    private int score;

    public SinglePlayerBoard(int width, int height, int scoreIncrementAmount, Ball ball, Paddle paddle1) {
        super(width, height, scoreIncrementAmount, ball, paddle1);
    }

    @Override
    protected void onHitPaddle() {
        onHitRightWall();
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
