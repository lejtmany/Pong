package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import model.AbstractModel;


public class PongPanel extends JPanel {
    
    private final AbstractModel board;
    

    public PongPanel(AbstractModel board) {
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