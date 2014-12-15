package model;

import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PaddleTest {
    
    Paddle defaultPaddle;
    
    @Before
    public void setUp() {
        defaultPaddle = new Paddle(new Point(1, 1), 10, 2);
    }

    @Test
    public void testMoveDown() {
        defaultPaddle.setSpeed(1);
        defaultPaddle.move(Direction.DOWN);
        assertEquals(2, defaultPaddle.getY());
    }

    @Test
    public void testMoveUp() {
        defaultPaddle.setSpeed(1);
        defaultPaddle.move(Direction.UP);
        assertEquals(0, defaultPaddle.getY());
    }
    
}