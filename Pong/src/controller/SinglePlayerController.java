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
import model.SinglePlayerBoard;
import view.PongGUI;

public class SinglePlayerController extends BoardController {

    SinglePlayerBoard board;
    RecordKeeper recordKeeper;
    

    public SinglePlayerController(SinglePlayerBoard board, PongGUI gui, RecordKeeper recordKeeper) {
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
            System.out.println(highScores.size());
            
            if(playerScore > highScores.get(0).score){
                highScores.set(0, createPlayer(playerScore));
                recordKeeper.updateRecords(highScores);
            }
            
              
            StringBuilder sb = new StringBuilder();
            for(Player p : recordKeeper.getRecords())
                sb.insert(0, String.format("%s : %d%n", p.name, p.score));
            JOptionPane.showMessageDialog(null, sb.toString(), "High Scores", 1);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(SinglePlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Player createPlayer(int playerScore) {
        String playerName;
        do{
            playerName = JOptionPane.showInputDialog("Please enter THREE initials: ");
            if(playerName == null){
                playerName = "QQQ";
        }
        }while(playerName.length() > 4);

        
        return new Player(playerName, playerScore);  
    }

}
