package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import model.ModelMain;

/**
 * @author Yosef Friedman & Yosef Lejtman
 */
public class PongPanel extends JPanel {
    
    private final ModelMain board;
    

    public PongPanel(ModelMain board) {
        this.board = board;
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g = (Graphics2D)grphcs;
        PongComponentDrawer drawer = new PongComponentDrawer();
        drawer.draw(board.getBall(), g);
        drawer.draw(board.getPaddles(), g);
    }
  
}