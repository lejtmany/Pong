package controller;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Ball;
import model.HighScoreRecordKeeper;
import model.Paddle;
import model.RecordKeeper;
import model.SinglePlayerModel;
import model.TwoPlayerModel;
import view.PongGUI;

/*
 * Ben Forgy
 * Dec 7, 2014
 */
public class Pong {

    private int ballRadius = 5;
    private int paddleLength = 50;
    private int paddleWidth = 5;
    private int scoreIncrement = 1;
    private double ballDeltaX = 1;
    private double ballDeltaY = .45;

    public void playOnePlayerGame(Dimension bounds, Ball gameBall, Paddle paddle) {

        SinglePlayerModel spb = new SinglePlayerModel(bounds, gameBall, paddle);
        PongGUI pongGui = new PongGUI(spb);
        RecordKeeper recordKeeper = null;

        String[] options = {"Beginner", "Intermediate", "Advanced"};
        int choice = JOptionPane.showOptionDialog(null, "Chose a difficulty level.", "Difficulty",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, null);

        String highscoreFileName = "Pong\\..\\cache\\highscores.hsf";
        if (choice == 0) {
            spb.setDefaultBallSpeedFactor(1);
            highscoreFileName = "Pong\\..\\cache\\highscores_1.hsf";
        } else if (choice == 1) {
            spb.setDefaultBallSpeedFactor(2);
            highscoreFileName = "Pong\\..\\cache\\highscores_2.hsf";
        } else if (choice == 2) {
            spb.setDefaultBallSpeedFactor(2.5);
            highscoreFileName = "Pong\\..\\cache\\highscores_3.hsf";
        }
        spb.setPaddleSpeed(2.5);

        try {
            recordKeeper = new HighScoreRecordKeeper(highscoreFileName, 3);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unable to set up File I/O. No highscores.");
        }

        SinglePlayerController singlePlayerGame = new SinglePlayerController(spb, pongGui, recordKeeper);
        singlePlayerGame.start();
    }

    private void playTwoPlayerGame(Dimension bounds, Ball ball, Paddle[] paddles) {

        TwoPlayerModel twoPlayerModel = new TwoPlayerModel(bounds, ball, paddles[0], paddles[1]);
        twoPlayerModel.setPaddleSpeed(2.5);
        PongGUI pongGui = new PongGUI(twoPlayerModel);
        TwoPlayerController twoPlayerGame = new TwoPlayerController(twoPlayerModel, pongGui);
        twoPlayerGame.start();
    }

    public void startGame() {

        Dimension gameBounds = new Dimension(500, 500);
        Ball ball = new Ball(new Point(gameBounds.height / 2, gameBounds.height / 2), ballRadius);
        double paddleDelta = 2.5;
        ball.setDeltaX(ballDeltaX);
        ball.setDeltaY(ballDeltaY);
        Paddle[] paddles = new Paddle[2];

        paddles[0] = new Paddle(new Point(20, gameBounds.height / 2),
                paddleLength, paddleWidth);
        paddles[1] = new Paddle(
                new Point(gameBounds.width - (20 + paddleWidth), gameBounds.height / 2),
                paddleLength, paddleWidth);

        String[] options = {"One Player", "Two Player"};
        int choice = JOptionPane.showOptionDialog(null, "One Player or Two?", "Game type",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, null);

        if (choice == 0) {
            playOnePlayerGame(gameBounds, ball, paddles[0]);
        } else if (choice == 1) {
            playTwoPlayerGame(gameBounds, ball, paddles);
        }
    }

}
