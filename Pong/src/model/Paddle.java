package model;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Paddle {

    private double deltaY = 1;
    private boolean isRightPaddle;
    private boolean isMovingDown;
    private boolean isMovingUp;
    private final Rectangle2D.Double body;

    public Paddle(Point topLeft, int length, int width) {
        body = new Rectangle2D.Double(topLeft.x, topLeft.y, width, length);
    }

    public Rectangle getBody() {
        return new Rectangle((int) body.x, (int)body.y, (int)body.width,(int) body.height);
    }
    
    public void setSpeed(double newSpeed){
        deltaY = newSpeed;
    }

    public int getX(){
        return (int)body.x;
    }
    public int getY(){
        return (int)body.y;
    }
    public int getWidth() {
        return (int) body.width;
    }
    public int getHeight() {
        return (int) body.height;
    }

    public void moveDown() {
        body.y += deltaY;
    }

    public void moveUp() {
        body.y -= deltaY;
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
