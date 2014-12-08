package controller;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Paddle;
import model.Player;
import model.RecordKeeper;
import model.SinglePlayerModel;
import view.PongGUI;

public class SinglePlayerController extends BoardController {

    SinglePlayerModel board;
    RecordKeeper recordKeeper;
    

    public SinglePlayerController(SinglePlayerModel board, PongGUI gui, RecordKeeper recordKeeper) {
        super(board, gui);
        this.board = board;
        this.recordKeeper = recordKeeper;
        
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

    @Override
    protected void gameOver() {
        gui.displayGameOver();
        System.out.println("GAME OVER! ");
        
        if(recordKeeper != null)
            checkScore();
      
    }

    private void checkScore(){
        int playerScore = board.getScore();
        
        try {
            List<Player> highScores = recordKeeper.getRecords();
            
            if(isAHighScore(playerScore, highScores)){
                updateHighScores(highScores, playerScore);
            }         
            gui.displayRecordsPane(recordKeeper);
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
    private String promptForPlayerName() throws HeadlessException {
        String playerName;
        int maxInitialLength = 4;
        do{
            playerName = JOptionPane.showInputDialog("Please enter THREE initials: ").trim();
        }while(playerName == null || playerName.length() > maxInitialLength); //null check must come first to avoid nullpointer exception
        return playerName;  
    }

}