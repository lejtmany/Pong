

package model;

import java.awt.Dimension;
import java.awt.Point;
import org.junit.Test;
import static org.mockito.Mockito.*;


public class ModelTest {
    
    @Test
    public void testBallBouncesOffPaddle(){
        Ball ball = new Ball(new Point(11,10), 1, -1, 0);
        Paddle paddle = new Paddle(new Point(10,10), 5, 5);
        SinglePlayerModel spm = new SinglePlayerModel(new Dimension(500,500), ball, paddle);
        spm.updateBoard();
        
    }
    
}
