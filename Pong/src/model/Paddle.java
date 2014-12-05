package model;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * @author Yosef Friedman & Yosef Lejtman
 */
public class Paddle {

    private final int deltaY;
    private boolean isRightPaddle;
    private boolean isMovingDown;
    private boolean isMovingUp;
    private Rectangle body;

    public Paddle(Point top, int length, int width, int deltaY) {
        this.deltaY = deltaY;
        body = new Rectangle(top.x, top.y, width, length);
    }

    public Rectangle getBody() {
        return new Rectangle(body);
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return body.width;
    }
    public int getHeight() {
        return body.height;
    }

    public void moveDown() {
        body.translate(0, deltaY);
    }

    public void moveUp() {
        body.translate(0, -deltaY);
    }

    /**
     * @return the isRightFacing
     */
    public boolean isRightPaddle() {
        return isRightPaddle;
    }

    public void setRightPaddle(boolean isRightPaddle) {
        this.isRightPaddle = isRightPaddle;
    }

    /**
     * @param isMovingDown the isMovingDown to set
     */
    public void setIsMovingDown(boolean isMovingDown) {
        this.isMovingDown = isMovingDown;
    }

    /**
     * @param isMovingUp the isMovingUp to set
     */
    public void setIsMovingUp(boolean isMovingUp) {
        this.isMovingUp = isMovingUp;
    }

    /**
     * @return the isMovingDown
     */
    public boolean isMovingDown() {
        return isMovingDown;
    }

    /**
     * @return the isMovingUp
     */
    public boolean isMovingUp() {
        return isMovingUp;
    }
}
