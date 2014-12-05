package model;

import java.io.Serializable;

public class Player implements Serializable, Comparable<Player>{
    public final String name;
    public final int score;
    public Player(String _name, int _score){
        name = _name;
        score = _score;
    }

    @Override
    public int compareTo(Player otherGuy) {
        return this.score - otherGuy.score;
    }
}
