
package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import model.AbstractModel;
import model.Paddle;
import view.PongGUI;

abstract public class BoardController {

    protected final AbstractModel board;
    protected final PongGUI gui;
    public static final int updateTimesPerSecond = 1000;
    public static final long UPDATE_FREQUENCY = 1000 / updateTimesPerSecond;
    private final Timer gameLoop = new Timer();

    public BoardController(AbstractModel board, PongGUI gui) {
        this.board = board;
        this.gui = gui;
    }

    public void start() {
        startGameLoop();
    }

    private void startGameLoop() {
        gameLoop.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() { 
                updateBoard();
                updateView();
                if (board.isGameOver()) {                   
                    gameLoop.cancel();
                    gameOver();
                }
            }
        },
                0,
                UPDATE_FREQUENCY);
    }

    public void updateBoard() {
        board.updateBoard();
    }

    public void updateView() {
        updateScoreLabel();
        gui.refreshScreen();
    }

    protected abstract void updateScoreLabel();

    public int getUpdateTimesPerSecond() {
        return updateTimesPerSecond;
    }

    protected abstract void addPaddleKeyListeners();

    protected KeyListener generatePaddleKeyListeners(Paddle paddle, int upKey, int downKey) {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == upKey) {
                    paddle.setIsMovingUp(true);
                }

                if (ke.getKeyCode() == downKey) {
                    paddle.setIsMovingDown(true);
                }

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == upKey) {
                    paddle.setIsMovingUp(false);
                }
                if (ke.getKeyCode() == downKey) {
                    paddle.setIsMovingDown(false);
                }
            }
        };

    }

    protected abstract void gameOver();
}
