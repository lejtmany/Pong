package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.ModelMain;

/**
 * @author Yosef Friedman & Yosef Lejtman
 */
public class PongGUI {
    
    private final JFrame frame;
    private final PongPanel panel;
    private final JLabel scoreLabel;
    private final JPanel scorePanel;
    
    
    public PongGUI(ModelMain pongGame) {
        final int scoreFontSize = 20;
        final int scorePanelHeight = scoreFontSize + 10;
        Dimension gameDimensions = pongGame.getGameDimensions();
        
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(gameDimensions.width, gameDimensions.height + scorePanelHeight);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        
        panel = new PongPanel(pongGame);
        panel.setSize(gameDimensions.width, gameDimensions.height);
        panel.setPreferredSize(new Dimension(gameDimensions.width, gameDimensions.height));
        panel.setBackground(Color.black);
        panel.setFocusable(true);
        panel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        frame.add(panel, BorderLayout.CENTER);
                     
        scorePanel = new JPanel();
        scorePanel.setSize(gameDimensions.width, scorePanelHeight);
        scorePanel.setPreferredSize(
                new Dimension(gameDimensions.width, scorePanelHeight));
        scorePanel.setBackground(Color.black);
        frame.add(scorePanel, BorderLayout.NORTH);
        
               
        scoreLabel = new JLabel();
        scoreLabel.setSize(gameDimensions.width,scorePanelHeight);
        scoreLabel.setPreferredSize(
                new Dimension(gameDimensions.width, scorePanelHeight));
        scoreLabel.setFont(
                new Font(Font.SANS_SERIF, Font.BOLD, scoreFontSize));
        scoreLabel.setBackground(Color.black);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setForeground(Color.white);
        scorePanel.add(scoreLabel);
                
        frame.pack();
        frame.setVisible(true);
    }
        
    public JLabel getScoreLabel(){
        return scoreLabel;
    }
    
    public void refreshScreen() {
        panel.repaint();
    }
    
    public void displayGameOver() {
        JLabel gameOverLabel = new JLabel();
        String gameOverMessage = "Game Over!";
        gameOverLabel.setSize(panel.getSize());
        gameOverLabel.setBackground(Color.black);
        gameOverLabel.setForeground(Color.white);
        gameOverLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setText(gameOverMessage);
        panel.add(gameOverLabel);
        panel.repaint();
    }
    
    public PongPanel getPongPanel(){
        return panel;
    }
}