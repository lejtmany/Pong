package model;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Paddle {

    private double deltaY = 1;
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
    
    /**
     * Getters to avoid needless complexity on client side.
     * @return simplicity
     */
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

    public void move(Direction dir) {
        if(dir == Direction.DOWN)
            body.y += deltaY;
        else if(dir == Direction.UP)
            body.y -= deltaY;
        
    }
}
