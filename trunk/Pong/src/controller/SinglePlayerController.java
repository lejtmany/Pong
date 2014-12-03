package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.Board;
import model.Paddle;
import model.SinglePlayerBoard;
import view.PongGUI;

public class SinglePlayerController extends BoardController {

    SinglePlayerBoard board;

    public SinglePlayerController(SinglePlayerBoard board, PongGUI gui) {
        super(board, gui);
        this.board = board;
        addPaddleKeyListeners();
    }

    @Override
    protected void addPaddleKeyListeners() {
        Paddle paddle = super.board.getPaddles().get(0);
        super.gui.getPongPanel().addKeyListener(generatePaddleKeyListeners(paddle, KeyEvent.VK_UP, KeyEvent.VK_DOWN));
    }

    @Override
    protected void updateScoreLabel() {
        gui.getScoreLabel().setText("Score:" + board.getScore());
    }

}
