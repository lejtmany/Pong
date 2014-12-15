
package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import model.AbstractModel;
import model.Direction;
import model.PaddlePlayer;
import view.PongGUI;

abstract public class BoardController {

    protected final AbstractModel board;
    protected final PongGUI gui;
    public final int updatesPerSecond;
    private final long timerDelay;
    private final Timer gameLoop = new Timer();
    private final Direction[] paddleDirections = new Direction[2];

    public BoardController(AbstractModel board, PongGUI gui, int updatesPerSec)
    {
        this.board = board;
        this.gui = gui;
        updatesPerSecond = updatesPerSec;
        timerDelay = 1000 / updatesPerSecond;
    }

    public void start() {
        startGameLoop();
    }
    
    public int getUpdatesPerSecond(){
        return updatesPerSecond;
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
                timerDelay);
    }

    public abstract void updateBoard();

    public void updateView() {
        updateScoreLabel();
        gui.refreshScreen();
    }
    
    public Direction getDirection(PaddlePlayer paddle){
        return paddleDirections[paddle.ordinal()];
    }

    protected abstract void updateScoreLabel();

    public int getUpdateTimesPerSecond() {
        return updatesPerSecond;
    }

    protected abstract void addPaddleKeyListeners();

    protected KeyListener generatePaddleKeyListeners(PaddlePlayer paddle,
                                                    int upKey, int downKey) {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == upKey) {
                    paddleDirections[paddle.ordinal()] = Direction.UP;
                }

                if (ke.getKeyCode() == downKey) {
                    paddleDirections[paddle.ordinal()] = Direction.DOWN;
                }

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                paddleDirections[paddle.ordinal()] = Direction.NONE;
            }
        };

    }

    protected abstract void gameOver();
}
