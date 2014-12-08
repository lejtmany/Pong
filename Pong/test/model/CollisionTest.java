package model;

import java.awt.Point;
import org.junit.Assert;
import org.junit.Test;
import view.PongGUI;
import static org.mockito.Mockito.*;

/**
 * @author Yosef Friedman & Yosef Lejtman
 */
public class CollisionTest {
   
    @Test
    public void testIsHittingRightWall(){ 
        int deltaX = 1;
        Ball ball = new Ball(new Point(9,2), 1, deltaX, 0);
        Paddle paddle = new Paddle(new Point(2,0),3,1);        
        AbstractModel board = new AbstractModel(10,10,ball,paddle,1);
        SinglePlayerModel bc =
                new SinglePlayerModel(board, mock(PongGUI.class));
        bc.updateBoard();
        Assert.assertTrue(ball.getDeltaX() == -deltaX);
    }
        
    @Test
    public void testIsHittingLeftWall(){
        int deltaX = -1;
        Ball ball = new Ball(new Point(1,2), 1, deltaX, 0);
        Paddle paddle = new Paddle(new Point(2,0),3,1);        
        AbstractModel board = new AbstractModel(10,10,ball,paddle,1);
        SinglePlayerModel bc =
                new SinglePlayerModel(board, mock(PongGUI.class));
        bc.updateBoard();
        Assert.assertTrue(board.isGameOver());
    }
    
    @Test
    public void testIsHittingHorizontalWall(){
        int deltaY = -1;
        Ball ball = new Ball(new Point(1,2), 1, 0, deltaY);
        Paddle paddle = new Paddle(new Point(2,0),3,1);         
        AbstractModel board = new AbstractModel(10,10,ball,paddle,1);
        SinglePlayerModel bc =
                new SinglePlayerModel(board, mock(PongGUI.class));
        bc.updateBoard();
        Assert.assertTrue(ball.getDeltaY() == -deltaY);
    }
    
    @Test
    public void testIsHittingPaddle(){
        int deltaX = -1;
        int scoreIncrement = 2;
        Ball ball = new Ball(new Point(3,1), 1, deltaX, 0);
        Paddle paddle = new Paddle(new Point(2,0), 5, 4);        
        AbstractModel board = new AbstractModel(10,10,ball,paddle,scoreIncrement);
        SinglePlayerModel bc =
                new SinglePlayerModel(board, mock(PongGUI.class));
        bc.updateBoard();
        Assert.assertTrue(board.getBall().getDeltaX() ==  -deltaX); 
        Assert.assertTrue(board.getScore() == scoreIncrement);
    }
}