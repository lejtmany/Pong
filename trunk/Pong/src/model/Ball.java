package model;

import java.awt.Point;

/**
 * @author Yosef Friedman & Yosef Lejtman
 */
public class Ball{
    
    private ExactPoint center;
    
    private final int radius;
    
    private double deltaX, deltaY;
    
    
    public Ball(Point center, int radius) {
        this.center = new ExactPoint(center.x, center.y);
        this.radius = radius;
    }
    
    public void updatePosition(double amount) {
        center.x = center.x + (amount * deltaX);
        center.y = center.y + (amount * deltaY);
    }
    
    public Point getCenter() {
        return new Point((int)center.x, (int)center.y);
    }
    
    public void setCenter(double x, double y){
        this.center = new ExactPoint(x, y);
    }
    
    public int getRadius() {
        return radius;
    }

    public double getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }

    public double getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }
    
    class ExactPoint{
        double x;
        double y;
        ExactPoint(double nX, double nY){
            x = nX;
            y = nY;
        }
    }
}