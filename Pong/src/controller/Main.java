package controller;

import model.SinglePlayerBoard;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import model.Ball;
import model.HighScoreRecordKeeper;
import model.Paddle;
import model.RecordKeeper;
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
        final String HIGHSCORES_FILE =
                "C:\\Users\\BenForgy\\Documents\\NetBeansProjects\\trunk\\Pong\\cache\\highscores.bin";


        if (onePlayerMode) {
            Ball ball = new Ball(
                    new Point(gameWidth / 4, gameHeight / 2),
                    ballRadius, ballDelta, ballDelta);
            Paddle paddle = new Paddle(
                    new Point(20, gameHeight/2),
                    paddleLength, paddleWidth, 1);
            SinglePlayerBoard spb = new SinglePlayerBoard(500, 500, 1, ball, paddle);
            
            PongGUI pongGui = new PongGUI(spb);
            
            RecordKeeper recordKeeper = null;
            
            try {
                recordKeeper = new HighScoreRecordKeeper(HIGHSCORES_FILE, 3);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Unable to set up File I/O. No highscores.");
            }
            
            SinglePlayerController spc = new SinglePlayerController(spb, pongGui, recordKeeper);
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
