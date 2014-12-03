package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import model.Ball;
import model.Paddle;

public class PongComponentDrawer {

    public void draw(Ball ball, Graphics2D g) {
        Point topLeft = getBallTopLeftRect(ball);
        g.setColor(Color.white);
        g.fillOval(topLeft.x, topLeft.y, ball.getRadius() * 2, ball.getRadius() * 2);
    }

    private Point getBallTopLeftRect(Ball ball) {
        Point topLeft = new Point();
        topLeft.x = ball.getCenter().x - ball.getRadius();
        topLeft.y = ball.getCenter().y - ball.getRadius();
        return topLeft;
    }
    
     public void draw(List<Paddle> paddles, Graphics2D g){
        for(Paddle paddle : paddles){
            drawPaddle(paddle, g, paddle.isRightPaddle());
        }
    }
     
    private void drawPaddle(Paddle paddle, Graphics2D g, boolean isRightPaddle) {
        g.setColor(Color.white);             
        g.fillRect(getTopX(isRightPaddle, paddle), paddle.getTop().y, paddle.getWidth(), paddle.getLength());
    }

    private int getTopX(boolean isRightPaddle, Paddle paddle) {
        int topX = (isRightPaddle) ? paddle.getTop().x  : paddle.getTop().x - paddle.getWidth();
        return topX;
    }

}
