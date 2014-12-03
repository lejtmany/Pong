package model;

import java.awt.Point;

/**
 * @author Yosef Friedman & Yosef Lejtman
 */
public class Paddle {

    private final Point top;
    private final int length, width, deltaY;
    private boolean isRightPaddle;
    private boolean isMovingDown;
    private boolean isMovingUp;

    public Paddle(Point top, int length, int width, int deltaY) {
        this.top = top;
        this.length = length;
        this.width = width;
        this.deltaY = deltaY;
    }

    public Point getTop() {
        return new Point(top);
    }

    public int getLength() {
        return length;
    }

    public void moveDown() {
        top.translate(0, deltaY);
    }

    public void moveUp() {
        top.translate(0, -deltaY);
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
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
