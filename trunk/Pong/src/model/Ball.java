package model;

import java.awt.Point;

/**
 * @author Yosef Friedman & Yosef Lejtman
 */
public class Ball{
    
    private Point center;
    
    private final int radius;
    
    private double deltaX, deltaY;
    
    public Ball(Point center, int radius, double deltaX, double deltaY) {
        this.center = center;
        this.radius = radius;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }
    
    public void updatePosition() {
        center.translate((int)deltaX, (int) deltaY);
    }
    
    public Point getCenter() {
        return new Point(center);
    }
    
    public void setCenter(Point center){
        this.center = center;
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
}