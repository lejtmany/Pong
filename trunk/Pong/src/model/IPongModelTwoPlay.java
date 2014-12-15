package model;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public interface IPongModelTwoPlay
{
    void updateBall();
    void movePaddle(PaddlePlayer paddlePlayer, Direction dir);
    void incrementScore(PaddlePlayer paddlePlayer);
    void restart();
    Rectangle getPaddle(PaddlePlayer player);
    Ellipse2D getBall();
    void resetBall(PaddlePlayer losingPlayer);
}
