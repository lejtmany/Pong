package controller;

import model.SinglePlayerBoard;
import java.awt.Point;
import model.Ball;
import model.Board;
import model.Paddle;
import model.TwoPlayerBoard;
import view.PongGUI;

/**
 * @author Yosef Friedman & Yosef Lejtman
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int gameWidth = 500;
        final int gameHeight = 500;
        final int ballRadius = 5;
        final int paddleLength = 50;
        final int paddleWidth = 5;
        final int scoreIncrement = 1;
        final int ballDelta = 1;
        final boolean onePlayerMode = true;

        if (onePlayerMode) {
            Ball ball = new Ball(
                    new Point(gameWidth / 4, gameHeight / 2),
                    ballRadius, ballDelta, ballDelta);
            Paddle paddle = new Paddle(
                    new Point(20, gameHeight/2),
                    paddleLength, paddleWidth, 1);
            SinglePlayerBoard spb = new SinglePlayerBoard(500, 500, 1, ball, paddle);
            PongGUI pongGui = new PongGUI(spb);
            SinglePlayerController spc = new SinglePlayerController(spb, pongGui);
            spc.start();
        } else {
            Ball ball = new Ball(
                    new Point(gameWidth / 4, gameHeight / 2),
                    ballRadius, ballDelta, ballDelta);
            Paddle paddle1 = new Paddle(
                    new Point(20, gameHeight/2),
                    paddleLength, paddleWidth, 1);
            Paddle paddle2 = new Paddle(
                    new Point(gameWidth - (20 + paddleWidth), gameHeight/2),
                    paddleLength, paddleWidth, 1);
            TwoPlayerBoard tpb = new TwoPlayerBoard(500, 500, 1, 3, ball, paddle1, paddle2);
            PongGUI pongGui = new PongGUI(tpb);
            TwoPlayerController tpc = new TwoPlayerController(tpb, pongGui);
            tpc.start();
        }

    }
}
