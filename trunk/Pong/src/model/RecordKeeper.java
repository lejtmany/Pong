package model;

import java.io.IOException;
import java.util.List;

public interface RecordKeeper {
    
    //Pointer to record bin set in constructor
    
    void setPath(String path) throws IOException;
    
    /**
     * @return an ORDERED (lowest score to highest) list of the players.
     * @throws IOException since it is dealing with file input.
     */
    List<Player> getRecords() throws IOException;
    
    /**
     * NOTE: Completely rewrites the file.
     * @param scores array (NEED NOT BE ORDERED) of players to write to file.
     * @return true if save was successful.
     * @throws IOException since it is dealing with file output.
     */
    boolean updateRecords(List<Player> scores) throws IOException;
    
    String recordsToHTMLString();

}
