package model;

import java.awt.Dimension;
import java.awt.Rectangle;

public class SinglePlayerModel extends AbstractModel implements IPongModelOnePlay{

    private int score;
    private Paddle paddle;

    public SinglePlayerModel(Dimension gameDimensions, Ball ball, Paddle paddle1) {
        super(gameDimensions, ball);
        paddle = paddle1;
    }
    
    @Override
    public void setPaddleSpeed(double pxlPerMove){
        paddle.setSpeed(pxlPerMove);   
    }

    @Override
    protected void onHitPaddle() {
        super.onHitPaddle();
        incrementScore();
    }

    @Override
    protected void onHitLeftWall() {
        gameOver();
    }

    @Override
    protected void onHitRightWall() {
        ball.setDeltaX(-ball.getDeltaX());
    }

    @Override
    protected void gameOver() {
        setGameOver(true);
    }

    @Override
    public void incrementScore() {
        score += scoreIncrementAmount;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void movePaddle(Direction dir) {
        if(!paddleCollidingWithWall(paddle, dir))
            paddle.move(dir);
    }

    @Override
    public void restart() {
        score = 0;
        resetBall();
    }

    @Override
    public Rectangle[] getPaddles() {
        return new Rectangle[]{paddle.getBody()};
    }

    @Override
    public Rectangle getPaddle() {
        return paddle.getBody();
    }
}
