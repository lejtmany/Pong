package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HighScoreRecordKeeper implements RecordKeeper{
    
    private File file;
    private final int numOfScores;
    
    public HighScoreRecordKeeper(String fileName, int numberOfScores) throws IOException{
        setPath(fileName);
        numOfScores = numberOfScores;
    }
    
    @Override
    public void setPath(String fileName) throws IOException{
        file = new File(fileName);
        if(!file.exists() || file.length() < 12){
            
            setUpDefaultScores();
        }
    }

    @Override
    public List<Player> getRecords(){
        ArrayList<Player> players = new ArrayList<>();
        
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
            Object inputObject = null;
            
            for(int i = 0; i < numOfScores; i++){
                try {
                    inputObject = reader.readObject();
                } catch (ClassNotFoundException ex) {
                    throw new IOException("Could not read objects from file: " + ex.toString());
                }
                
                if(inputObject instanceof Player){
                    players.add((Player) inputObject);
                } 
            }
        } catch (IOException ex) {
            Logger.getLogger(HighScoreRecordKeeper.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.sort(players);
        return players;
    }

    @Override
    public boolean updateRecords(List<Player> scores) throws IOException {
        
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))) {
            for(Player player : scores){
                writer.writeObject(player);
            }
        }
        return true;
    }
    
    @Override
    public String recordsToHTMLString(){
        StringBuilder sb = new StringBuilder();
        
            for(Player p : getRecords()){
                if(p.score > 0)
                    sb.insert(0, 
                            String.format("%s         %d<br/>",
                                        p.name, p.score));
            }
            sb.insert(0, "<html>High scores: <br/>");
            sb.append("</html>");
            return sb.toString();
    }
    
    private void setUpDefaultScores() throws IOException{
        List<Player> players = new ArrayList<>(3);
        players.add(new Player("   ", 0));
        players.add(new Player("   ", 0));
        players.add(new Player("   ", 0));
        updateRecords(players);
    }
    
}
