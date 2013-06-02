    import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
    
    public class ModelHighscore {
        
        private static final int MAX_NUMBER_SCORES_PER_DIFFICULTY = 5;
        SudokuModel model;
        File highscoreFile;
        ArrayList<ModelScore> easyScores;
        ArrayList<ModelScore> intermediateScores;
        ArrayList<ModelScore> hardScores;
       
        public ModelHighscore(SudokuModel model) {
            this.model = model;
            this.highscoreFile = new File("highscore"); // current directory/highscore <----- THIS LINE
            this.loadHighscores();
        }
            
        private void loadHighscores() {
            try {
                Scanner s = new Scanner(highscoreFile);
                String string = "";
                String difficulty = "";
                while(s.hasNextLine()) {
                    string = s.nextLine();
                    if(string.equals("Easy")) {
                        difficulty = "Easy";
                    } else if(string.equals("Intermediate")) {
                        difficulty = "Intermediate";
                    } else if(string.equals("Hard")) {
                        difficulty = "Hard";
                    } else {
                        if(difficulty.equals("Easy")) {
                            this.easyScores.add(new ModelScore(difficulty, s.next(), s.nextLong()));
                        } else if(difficulty.equals("Intermediate")) {
                            this.intermediateScores.add(new ModelScore(difficulty, s.next(), s.nextLong()));
                        } else if(difficulty.equals("Hard")) {
                            this.hardScores.add(new ModelScore(difficulty, s.next(), s.nextLong()));
                        }
                    }
                }
            } catch (FileNotFoundException e) {

            } catch (NoSuchElementException e) {
                
            }
        }
       
        public void saveHighscores() {
            /*
            Easy
            Name                   Time
            Name                   Time
            Name                   Time
            Name                   Time
            Name                   Time

            Intermediate
            Name                   Time
            Name                   Time
            Name                   Time
            Name                   Time
            Name                   Time

            Hard:
            Name                   Time
            Name                   Time
            Name                   Time
            Name                   Time
            Name                   Time
            */
            String newline = System.getProperty("line.separator");
            String saveString = "";
        
            saveString = saveString + "Easy" + newline;
            for(int i=0; i<this.easyScores.size(); i++) {
                saveString = saveString + easyScores.get(i).getName() + easyScores.get(i).getTime() + newline;
            }
            saveString = newline + saveString + "Intermediate" + newline;
            for(int i=0; i<this.intermediateScores.size(); i++) {
                saveString = saveString + intermediateScores.get(i).getName() + intermediateScores.get(i).getTime() + newline;
            }
            saveString = newline + saveString + "Hard" + newline;
            for(int i=0; i<this.hardScores.size(); i++) {
                saveString = saveString + hardScores.get(i).getName() + hardScores.get(i).getTime() + newline;
            }
            
            try {
                PrintWriter print = new PrintWriter(highscoreFile);
                print.write(saveString);
                print.close();
            } catch (FileNotFoundException e) {}
        }
        
        public void addScore(ModelScore score) {
            if(score.getDifficulty().equals("Easy")) {
                for(int i=0; i<this.easyScores.size(); i++) {
                    if(score.getTime() < easyScores.get(i).getTime()) {
                        easyScores.set(i, score);
                    }
                }
                while(easyScores.size()>MAX_NUMBER_SCORES_PER_DIFFICULTY) {
                    easyScores.remove(MAX_NUMBER_SCORES_PER_DIFFICULTY);
                }
            } else if(score.getDifficulty().equals("Intermediate")) {
                for(int i=0; i<this.intermediateScores.size(); i++) {
                    if(score.getTime() < intermediateScores.get(i).getTime()) {
                        intermediateScores.set(i, score);
                    }
                }
                while(intermediateScores.size()>MAX_NUMBER_SCORES_PER_DIFFICULTY) {
                    intermediateScores.remove(MAX_NUMBER_SCORES_PER_DIFFICULTY);
                }                
            } else if(score.getDifficulty().equals("Hard")) {
                for(int i=0; i<this.hardScores.size(); i++) {
                    if(score.getTime() < hardScores.get(i).getTime()) {
                        hardScores.set(i, score);
                    }
                }
                while(hardScores.size()>MAX_NUMBER_SCORES_PER_DIFFICULTY) {
                    hardScores.remove(MAX_NUMBER_SCORES_PER_DIFFICULTY);
                }
            }
        }
}
