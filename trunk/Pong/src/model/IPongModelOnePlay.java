package model;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

/**
 * Ben Forgy
 * Dec 9, 2014
 */

public interface IPongModelOnePlay {
    void updateBall();
    void movePaddle(Direction dir);
    void incrementScore();
    void restart();
    Rectangle getPaddle();
    Ellipse2D getBall();

}
