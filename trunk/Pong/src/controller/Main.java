package controller;

import java.awt.Dimension;
import model.SinglePlayerModel;
import java.awt.Point;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Ball;
import model.HighScoreRecordKeeper;
import model.Paddle;
import model.RecordKeeper;
import model.TwoPlayerModel;
import view.PongGUI;

/**
 * @author Yosef Friedman & Yosef Lejtman
 */
public class Main {
        final int ballRadius = 5;
        final int paddleLength = 50;
        final int paddleWidth = 5;
        final int scoreIncrement = 1;
        final double ballDeltaX = 1;
        final double ballDeltaY = .45;
        final String HIGHSCORES_FILE = "Pong\\..\\cache\\highscores.bin";
        
        
    public void playOnePlayerGame(Dimension bounds, Ball gameBall, Paddle paddle){         
            SinglePlayerModel spb = new SinglePlayerModel(bounds, gameBall, paddle);
            PongGUI pongGui = new PongGUI(spb);
            RecordKeeper recordKeeper = null;
            
            try {
                recordKeeper = new HighScoreRecordKeeper(HIGHSCORES_FILE, 3);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Unable to set up File I/O. No highscores.");
            }
            
            SinglePlayerController singlePlayerGame = new SinglePlayerController(spb, pongGui, recordKeeper);
            singlePlayerGame.start();
    }
    
    
    private void playTwoPlayerGame(Dimension bounds, Ball ball, Paddle[] paddles) {
        
        TwoPlayerModel twoPlayerGame = new TwoPlayerModel(bounds, ball, paddles[0], paddles[1]);
        PongGUI pongGui = new PongGUI(twoPlayerGame);
        TwoPlayerController tpc = new TwoPlayerController(twoPlayerGame, pongGui);
        tpc.start();
    }
        
    
    public void startGame(){
        
        Dimension gameBounds = new Dimension(500, 500);
        Ball ball = new Ball(new Point(gameBounds.height/2, gameBounds.height/2), ballRadius);
        ball.setDeltaX(ballDeltaX);
        ball.setDeltaY(ballDeltaY);
        Paddle[] paddles = new Paddle[2];
                
        paddles[0] = new Paddle(new Point(20, gameBounds.height/2),
                    paddleLength, paddleWidth, 1);
         paddles[1] = new Paddle(
                    new Point(gameBounds.width - (20 + paddleWidth), gameBounds.height/2),
                    paddleLength, paddleWidth, 1);
        
        String[] options = {"One Player", "Two Player"};
        int choice = JOptionPane.showOptionDialog(null, "One Player or Two?",  "Game type",
                             JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                             null, options, null);
        
        if(choice == 0){
            playOnePlayerGame(gameBounds, ball, paddles[0]);
        }
        else if(choice == 1){
            playTwoPlayerGame(gameBounds, ball, paddles);
        }
    }
        
        
    public static void main(String[] args) {
        Main m = new Main();
        m.startGame();
    }
}
