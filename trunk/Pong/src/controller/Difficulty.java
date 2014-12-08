

package controller;

public enum Difficulty {
      
    Difficult(2.5,"Pong\\..\\cache\\highscores_Diffcult.hsf"), Intermediate(2,"Pong\\..\\cache\\highscores_Intermediate.hsf"), Beginner(1,"Pong\\..\\cache\\highscores_Beginner.hsf");
    
    private String highScoreFileName;
    private double speedFactor;
    
    Difficulty(double speedFactor, String highScoreFileName){
        this.speedFactor = speedFactor;
        this.highScoreFileName = highScoreFileName;
    }

    public String getHighScoreFileName() {
        return highScoreFileName;
    }

    public double getSpeedFactor() {
        return speedFactor;
    }
    
    public static String[] getDifficultyStrings(){
        String[] difficulties = new String[Difficulty.values().length];
        int counter = 0;
        for(Difficulty difficulty : Difficulty.values()){
            difficulties[counter++] = difficulty.toString();
        }
        return difficulties;
    }
}
