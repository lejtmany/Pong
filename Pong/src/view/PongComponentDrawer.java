package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class PongComponentDrawer {

    public static void draw(Ellipse2D ball, Graphics2D g) {
        g.setColor(Color.white);
        g.fillOval((int)ball.getMinX(), 
                (int)ball.getMinY(), 
                (int)ball.getWidth(), (int)ball.getHeight());
    }
    
     public static void draw(Rectangle[] paddles, Graphics2D g){
        for(Rectangle paddle : paddles){
            drawPaddle(paddle, g);
        }
    }
     
    private static void drawPaddle(Rectangle paddle, Graphics2D g) {
        g.setColor(Color.white);             
        g.fillRect(paddle.x ,paddle.y, paddle.width, paddle.height);
    }
}
