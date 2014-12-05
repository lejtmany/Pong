package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
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
            drawPaddle(paddle, g);
        }
    }
     
    private void drawPaddle(Paddle paddle, Graphics2D g) {
        Rectangle paddleBody = paddle.getBody();
        g.setColor(Color.white);             
        g.fillRect(paddleBody.x ,paddleBody.y, paddle.getWidth(), paddle.getHeight());
    }
}
