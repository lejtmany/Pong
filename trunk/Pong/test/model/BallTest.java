package model;

import java.awt.Point;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Yosef Friedman & Yosef Lejtman
 */
public class BallTest {
    
    @Test
    public void testUpdatePosition() {
        System.out.println("updatePosition");
        Point center = new Point(0, 0);
        Ball instance = new Ball(center, 1, 5, 5);
        instance.updatePosition();
        assertEquals(new Point(5, 5), instance.getCenter());
    }
}