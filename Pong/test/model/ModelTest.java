

package model;

import java.awt.Dimension;
import java.awt.Point;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;


public class ModelTest {
    
    @Test
    public void testBallBouncesOffPaddle(){
        Ball ball = new Ball(new Point(11,10), 1, -1, 0);
        Paddle paddle = new Paddle(new Point(10,10), 5, 5);
        SinglePlayerModel spm = new SinglePlayerModel(new Dimension(500,500), ball, paddle);
        spm.updateBall();
        assertThat(ball.getDeltaX()).isEqualTo(1);
    }
    
    @Test
     public void testBallBouncesOffPaddleWhenCenterWithinPaddle(){
        Ball ball = new Ball(new Point(13,10), 1, -2, 0);
        Paddle paddle = new Paddle(new Point(10,10), 5, 5);
        SinglePlayerModel spm = new SinglePlayerModel(new Dimension(500,500), ball, paddle);
        spm.updateBall();
        assertThat(ball.getDeltaX()).isEqualTo(2);
        //test that doesn't flip again
        spm.updateBoard();
        assertThat(ball.getDeltaX()).isEqualTo(2);
    }
     
      @Test
      //where the delta of ballis more than the width of the paddle the ball will not bounce
      // at this point the game would probably be unplayable anyway so we're not concerned about such a case
     public void testFailsBallBouncesOffPaddleWhenCenterJumpsPaddle(){
        Ball ball = new Ball(new Point(16,10), 1, -10, 0);
        Paddle paddle = new Paddle(new Point(10,10), 5, 5);
        SinglePlayerModel spm = new SinglePlayerModel(new Dimension(500,500), ball, paddle);
        spm.updateBall();
        assertThat(ball.getDeltaX()).isEqualTo(-10);
        //test that doesn't flip again
        spm.updateBall();
        assertThat(ball.getDeltaX()).isEqualTo(-10);
    }
     
     @Test
     public void testFailsBallBouncesOffSideOfPaddle(){
        Ball ball = new Ball(new Point(11,9), 1, -1, 1);
        Paddle paddle = new Paddle(new Point(10,10), 5, 5);
        SinglePlayerModel spm = new SinglePlayerModel(new Dimension(500,500), ball, paddle);
        spm.updateBall();
        assertThat(ball.getDeltaX()).isEqualTo(1);
        //test that doesn't flip again
        spm.updateBall();
        assertThat(ball.getDeltaX()).isEqualTo(1);
    }
    
}
