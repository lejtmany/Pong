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
import model.AbstractModel;


public class PongGUI {

    private final JFrame frame;
    private final PongPanel panel;
    private final JLabel scoreLabel;
    private final JPanel scorePanel;
    private final Dimension gameDimensions;

    public PongGUI(AbstractModel pongGame) {

        gameDimensions = pongGame.getGameDimensions();
        final int scoreFontSize = 20;
        final int scorePanelHeight = scoreFontSize + 10;

        frame = new JFrame();
        initializeFrame(scorePanelHeight);

        panel = new PongPanel(pongGame);
        initializePongPanel();
        frame.add(panel, BorderLayout.CENTER);

        scorePanel = new JPanel();
        initializeScorePanel(scorePanelHeight);
        frame.add(scorePanel, BorderLayout.NORTH);

        scoreLabel = new JLabel();
        initializeScoreLabel(scorePanelHeight, scoreFontSize);
        scorePanel.add(scoreLabel);

        frame.pack();
        frame.setVisible(true);
    }

    private void initializeFrame(final int scorePanelHeight) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(gameDimensions.width, gameDimensions.height + scorePanelHeight);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
    }

    private void initializePongPanel() {
        panel.setSize(gameDimensions.width, gameDimensions.height);
        panel.setPreferredSize(new Dimension(gameDimensions.width, gameDimensions.height));
        panel.setBackground(Color.black);
        panel.setFocusable(true);
        panel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
    }

    private void initializeScoreLabel(final int scorePanelHeight, final int scoreFontSize) {
        scoreLabel.setSize(gameDimensions.width, scorePanelHeight);
        scoreLabel.setPreferredSize(
                new Dimension(gameDimensions.width, scorePanelHeight));
        scoreLabel.setFont(
                new Font(Font.SANS_SERIF, Font.BOLD, scoreFontSize));
        scoreLabel.setBackground(Color.black);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setForeground(Color.white);
    }

    private void initializeScorePanel(final int scorePanelHeight) {
        scorePanel.setSize(gameDimensions.width, scorePanelHeight);
        scorePanel.setPreferredSize(
                new Dimension(gameDimensions.width, scorePanelHeight));
        scorePanel.setBackground(Color.black);
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    public void refreshScreen() {
        panel.repaint();
    }

    public void displayGameOver(String message) {
        JLabel gameOverLabel = new JLabel();
        gameOverLabel.setSize(panel.getSize());
        gameOverLabel.setBackground(Color.black);
        gameOverLabel.setForeground(Color.white);
        gameOverLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setText(String.format("<html>%s<br/><br/>%s</html>", "Game Over",message));
        panel.add(gameOverLabel);
        panel.repaint();
    }


    public PongPanel getPongPanel() {
        return panel;
    }
}
