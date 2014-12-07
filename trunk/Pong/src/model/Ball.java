package model;

import java.awt.Point;

/**
 * @author Yosef Friedman & Yosef Lejtman
 */
public class Ball{
    
    private Point center;
    
    private final int radius;
    
    private double deltaX, deltaY;
    private double functionalDeltaX, functionalDeltaY;
    
    
    public Ball(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }
    
    public void updatePosition() {
        if(functionalDeltaX > 1){
            functionalDeltaX--;
        }else if(functionalDeltaX < -1){
            functionalDeltaX++;
        }
        if(functionalDeltaY > 1){
            functionalDeltaY--;
        }else if(functionalDeltaY < -1){
            functionalDeltaY++;
        }
        functionalDeltaX += deltaX;
        functionalDeltaY += deltaY;
        center.translate((int)functionalDeltaX, (int)functionalDeltaY);
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
        functionalDeltaX = deltaX / Math.abs(deltaX);
    }

    public double getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
        functionalDeltaY = deltaY / Math.abs(deltaY);
    }
}