package model;

import java.awt.Dimension;
import java.awt.Rectangle;

public class TwoPlayerModel extends AbstractModel implements IPongModelTwoPlay{

    private int[] playerScores;
    private int gameUntil = 3;
    private Paddle[] paddles;

    public TwoPlayerModel(Dimension gameDimensions, Ball ball, Paddle... paddles) {
        super(gameDimensions, ball);
        this.paddles = paddles;
        playerScores = new int[2];
        
    }

    public void setGameUntilScore(int score) {
        gameUntil = score;
    }
    
    @Override
    public void setPaddleSpeed(double pxlPerMove){
        for(Paddle p : paddles){
            if(p != null)
                p.setSpeed(pxlPerMove);    
        }
    }

    @Override
    protected void onHitLeftWall() {
        incrementScore(PaddlePlayer.TWO);
        if (playerScores[1] == gameUntil) {
            gameOver();
        } else {
            resetBall(PaddlePlayer.ONE);
        }
    }

    @Override
    protected void onHitRightWall() {
        incrementScore(PaddlePlayer.ONE);
        if (playerScores[0] == gameUntil) {
            gameOver();
        } else {
            resetBall(PaddlePlayer.TWO);
        }
    }

    @Override
    protected void gameOver() {
        setGameOver(true);
    }

    @Override
    public void incrementScore(PaddlePlayer player) {
        playerScores[player.ordinal()] += scoreIncrementAmount;
    }
    
    public int getScore(PaddlePlayer player){
        return playerScores[player.ordinal()];
    }

    @Override
    public void movePaddle(PaddlePlayer player, Direction dir) {
        Paddle paddle = paddles[player.ordinal()];
        if(!paddleCollidingWithWall(paddle, dir))
            paddle.move(dir);   
    }

    @Override
    public void restart() {
        playerScores = new int[2];
        resetBall();
    }

    @Override
    public Rectangle getPaddle(PaddlePlayer player) {
        return paddles[player.ordinal()].getBody();
    }
    
   
    @Override
    public Rectangle[] getPaddles() {
        return new Rectangle[]{paddles[0].getBody(), paddles[1].getBody()};
    }

    @Override
    public void resetBall(PaddlePlayer losingPlayer) {
        double startingX = (losingPlayer == PaddlePlayer.TWO) ?
                gameDimensions.width / 3 
                : (gameDimensions.width - gameDimensions.width / 3);
        
        ball.setCenter(startingX, (gameDimensions.height / 2));
        resetBallSpeed();
    }

}
