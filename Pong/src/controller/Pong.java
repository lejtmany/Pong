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

public class Pong {

    final private int ballRadius = 5;
    final private int paddleLength = 50;
    final private int paddleWidth = 5;
    final private double ballDeltaX = 1;
    final private double ballDeltaY = 1;
    public final int updatesPerSecond = 200;
    private final double paddleDelta = 250.0 / updatesPerSecond;

    public void playOnePlayerGame(Dimension bounds, Ball gameBall, Paddle paddle) {

        SinglePlayerModel spb = new SinglePlayerModel(bounds, gameBall, paddle);
        PongGUI pongGui = new PongGUI(spb);
        RecordKeeper recordKeeper = null;
        Difficulty difficulty = null;

        difficulty = getDifficulty();

        spb.setDefaultBallSpeedFactor(difficulty.getSpeedFactor() / updatesPerSecond);
        spb.setPaddleSpeed(paddleDelta);

        recordKeeper = tryInitializeRecordKeeper(recordKeeper, difficulty);

        SinglePlayerController singlePlayerGame
                = new SinglePlayerController(spb, pongGui, recordKeeper, updatesPerSecond);
        singlePlayerGame.start();
    }

    public int getUpdatesPerSecond() {
        return updatesPerSecond;
    }

    private Difficulty getDifficulty() {
        Difficulty difficulty;
        int choice = JOptionPane.showOptionDialog(null, "Chose a difficulty level.", "Difficulty",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, Difficulty.getDifficultyStrings(), null);
        difficulty = Difficulty.values()[choice];
        return difficulty;
    }

    private RecordKeeper tryInitializeRecordKeeper(RecordKeeper recordKeeper,
                                                    Difficulty difficulty) {
        try {
            recordKeeper = new HighScoreRecordKeeper(difficulty.getHighScoreFileName(), 3);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, 
                        "Unable to set up File I/O. No highscores.");
        }
        return recordKeeper;
    }

    private void playTwoPlayerGame(Dimension bounds, Ball ball, Paddle[] paddles)
    {

        TwoPlayerModel twoPlayerModel = 
                new TwoPlayerModel(bounds, ball, paddles[0], paddles[1]);
        twoPlayerModel.setPaddleSpeed(paddleDelta);
        twoPlayerModel.setDefaultBallSpeedFactor(100.0 / updatesPerSecond);
        PongGUI pongGui = new PongGUI(twoPlayerModel);
        TwoPlayerController twoPlayerGame
                = new TwoPlayerController(twoPlayerModel, pongGui, updatesPerSecond);
        twoPlayerGame.start();
    }

    public void startGame() {

        Dimension gameBounds = new Dimension(500, 500);
        Ball ball = new Ball(new Point(gameBounds.height / 2,
                                        gameBounds.height / 2), ballRadius);
        int paddleOffWall = 20;
        ball.setDeltaX(ballDeltaX);
        ball.setDeltaY(ballDeltaY);
        Paddle[] paddles = initializePaddleArray(paddleOffWall, gameBounds);
        int choice = getGameType();

        startGame(choice, gameBounds, ball, paddles);
    }

    private void startGame(int choice, Dimension gameBounds, 
                                            Ball ball, Paddle[] paddles) 
    {
        switch (choice) {
            case 0:
                playOnePlayerGame(gameBounds, ball, paddles[0]);
                break;
            case 1:
                playTwoPlayerGame(gameBounds, ball, paddles);
                break;
        }
    }

    private int getGameType() {
        String[] options = {"One Player", "Two Player"};
        int choice = JOptionPane.showOptionDialog(null,
                "One Player or Two?", "Game type",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, null);
        return choice;
    }

    private Paddle[] initializePaddleArray(int paddleOffWall, Dimension gameBounds)
    {
        Paddle[] paddles = new Paddle[2];
        paddles[0] = new Paddle(
                new Point(paddleOffWall,
                        gameBounds.height / 2),
                paddleLength,
                paddleWidth);
        paddles[1] = new Paddle(
                new Point(
                        gameBounds.width - (paddleOffWall + paddleWidth),
                        gameBounds.height / 2),
                paddleLength,
                paddleWidth);
        return paddles;
    }

}
