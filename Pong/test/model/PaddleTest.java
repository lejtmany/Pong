package model;

import java.awt.Point;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Yosef Friedman & Yosef Lejtman
 */
public class PaddleTest {
    
    @Test
    public void testMoveDown() {
        System.out.println("moveDown");
        Point top = new Point(0, 50);
        Paddle instance = new Paddle(top, 50, 5);
        instance.moveDown();
        assertEquals(new Point(0, 55), instance.getTop());
    }

    @Test
    public void testMoveUp() {
        System.out.println("moveUp");
        Point top = new Point(0, 50);
        Paddle instance = new Paddle(top, 50, 5);
        instance.moveUp();
        assertEquals(new Point(0, 45), instance.getTop());
    }
}