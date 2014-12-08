

package model;

import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BallTest {
 
    Ball defaultBall;
    
    @Before
    public void setUp() {
        defaultBall = new Ball(new Point(5, 5), 5);
    }
    
    @Test
    public void testInit(){
        Ball ball = new Ball(new Point(0, 0), 4);
    }

    @Test
    public void testUpdatePosition_dx1_dy0() {
        double amount = 1.0;
        defaultBall.setDeltaX(1);
        defaultBall.updatePosition(amount);
        
        assertEquals(new Point(6, 5), defaultBall.getCenter());
    }
    
    @Test
    public void testUpdatePosition_dx0_dy1() {
        double amount = 1.0;
        defaultBall.setDeltaY(1);
        defaultBall.updatePosition(amount);
        
        assertEquals(new Point(5, 6), defaultBall.getCenter());
    }
    
    @Test
    public void testUpdatePosition_dxneg1_dy0() {
        double amount = 1.0;
        defaultBall.setDeltaX(-1);
        defaultBall.updatePosition(amount);
        
        assertEquals(new Point(4, 5), defaultBall.getCenter());
    }
    @Test
    public void testUpdatePosition_dx1_dyhalf() {
        double amount = 1.0;
        defaultBall.setDeltaX(1);
        defaultBall.setDeltaY(.5);
        defaultBall.updatePosition(amount);
        defaultBall.updatePosition(amount);
        
        assertEquals(new Point(7, 6), defaultBall.getCenter());
    }
    
}
