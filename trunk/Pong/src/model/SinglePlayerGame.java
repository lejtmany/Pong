package model;


public class SinglePlayerGame extends ModelMain {

    private int score;

    public SinglePlayerGame(int width, int height, int scoreIncrementAmount, Ball ball, Paddle paddle1) {
        super(width, height, scoreIncrementAmount, ball, paddle1);
    }

    @Override
    protected void onHitPaddle(Paddle paddle) {
        handleBallPaddleCollision(ball, paddle);
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
