package controller;

import java.awt.event.KeyEvent;
import model.PaddlePlayer;
import model.TwoPlayerModel;
import view.PongGUI;

public class TwoPlayerController extends BoardController{
    
    private TwoPlayerModel board;

    public TwoPlayerController(TwoPlayerModel board, 
                                PongGUI gui,
                                int updatesPerSec) {
        super(board, gui, updatesPerSec);
        this.board = board;
        addPaddleKeyListeners();
    }

    @Override
    protected void addPaddleKeyListeners() {
        super.gui.getPongPanel().addKeyListener(generatePaddleKeyListeners(
                PaddlePlayer.TWO, KeyEvent.VK_UP, KeyEvent.VK_DOWN));
        super.gui.getPongPanel().addKeyListener(generatePaddleKeyListeners(
                PaddlePlayer.ONE, KeyEvent.VK_W, KeyEvent.VK_S));
    }

    @Override
    protected void updateScoreLabel() {
       gui.getScoreLabel().setText(
               String.format("%s%-30d %s%d",
                       "Player 1: ", board.getScore(PaddlePlayer.ONE),
                       "Player 2: ", board.getScore(PaddlePlayer.TWO)));
    }

    @Override
    protected void gameOver() {
        String winner = 
            board.getScore(PaddlePlayer.ONE) > board.getScore(PaddlePlayer.TWO)?
                "Player 1 wins!" : "Player 2 wins!";
        gui.displayGameOver(winner);
    }

    @Override
    public void updateBoard() {
        board.updateBall();
        board.movePaddle(PaddlePlayer.ONE, getDirection(PaddlePlayer.ONE));
        board.movePaddle(PaddlePlayer.TWO, getDirection(PaddlePlayer.TWO));
    }
    
    
}
