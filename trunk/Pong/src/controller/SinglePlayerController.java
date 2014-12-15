package controller;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Direction;
import model.PaddlePlayer;
import model.Player;
import model.RecordKeeper;
import model.SinglePlayerModel;
import view.PongGUI;

public class SinglePlayerController extends BoardController {

    private SinglePlayerModel board;
    private RecordKeeper recordKeeper;
    

    public SinglePlayerController(SinglePlayerModel board, 
                                PongGUI gui,   
                                RecordKeeper recordKeeper,
                                int updatesPerSec) {
        super(board, gui, updatesPerSec);
        this.board = board;
        this.recordKeeper = recordKeeper;
        
        addPaddleKeyListeners();
    }

    @Override
    protected void addPaddleKeyListeners() {
        super.gui.getPongPanel().
                addKeyListener(generatePaddleKeyListeners(
                        PaddlePlayer.ONE, KeyEvent.VK_UP, KeyEvent.VK_DOWN));
    }

    @Override
    protected void updateScoreLabel() {
        gui.getScoreLabel().setText("Score:" + board.getScore());
    }

    @Override
    protected void gameOver() {          
        if(recordKeeper != null)
            checkScore();
        gui.displayGameOver(recordKeeper.recordsToHTMLString());
        System.out.println("GAME OVER!");
      
    }

    private void checkScore(){
        int playerScore = board.getScore();
        
        try {
            List<Player> highScores = recordKeeper.getRecords();
            
            if(isAHighScore(playerScore, highScores)){
                updateHighScores(highScores, playerScore);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(SinglePlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateHighScores(List<Player> highScores, int playerScore) throws IOException {
        highScores.set(0, createPlayer(playerScore));
        recordKeeper.updateRecords(highScores);
    }
    private static boolean isAHighScore(int playerScore, List<Player> highScores) {
        return playerScore > highScores.get(0).score;
    }

    private Player createPlayer(int playerScore) {
        String playerName = promptForPlayerName();
        return new Player(playerName, playerScore);  
    }
    private String promptForPlayerName(){
        String playerName;
        int reqLength = 4;
        do{
            playerName = JOptionPane.showInputDialog("Please enter THREE initials: ");
        }while(playerName == null || playerName.length() != reqLength); //null check must come first to avoid nullpointer exception
        return playerName;
    }

    @Override
    public void updateBoard() {
        board.updateBall();
        Direction dir = getDirection(PaddlePlayer.ONE);
        if(dir != Direction.NONE)
            board.movePaddle(getDirection(PaddlePlayer.ONE));
    }

}
