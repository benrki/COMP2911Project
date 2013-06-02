import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
 
 
public class ModelSaveLoad {
 
    SudokuModel model;
    ModelGrid currentGrid;
    ModelGrid answerGrid;
   
    public ModelSaveLoad(SudokuModel model, ModelGrid currentGrid, ModelGrid answerGrid) {
        this.model = model;
        this.currentGrid = currentGrid;
        this.answerGrid = answerGrid;
    }
   
    public String toString(ModelGrid string) {
        String sudoku = "";
        for (int i=0; i<ModelGrid.NUM_ROWS; i++) {
            for (int j=0; j<ModelGrid.NUM_COLS; j++) {
                if(string.grid.get(i).get(j).getNumber()==ModelCell.EMPTY) {
                    sudoku = sudoku + " | -";
                } else {
                   sudoku = sudoku + " | " + string.grid.get(i).get(j).getNumber();
                }
                if ((j+1)%3 == 0) {
                        sudoku = sudoku + " | ";
                }
            }
            sudoku = sudoku + System.getProperty("line.separator");
            if ((i+1)%3 == 0) {
                sudoku = sudoku + System.getProperty("line.separator");
            }
        }
        return sudoku;
    }
 
    public void saveGame(File save) {
        /*File parentDir = new File(location);
        parentDir.mkdir();
        File save = new File(parentDir, name + ".txt");
        try {
            save.createNewFile();
        } catch (IOException e) {
           
        }*/
        String newline = System.getProperty("line.separator");
        String original = "Original:" + newline;
        for (int i=0; i<ModelGrid.NUM_ROWS; i++) {
            for (int j=0; j<ModelGrid.NUM_COLS; j++) {
                if (currentGrid.grid.get(i).get(j).isGiven() == false) {
                        original = original + " | -";
                } else {
                        original = original + " | " + currentGrid.grid.get(i).get(j).getNumber();
                }
                if ((j+1)%3 == 0) {
                         original = original + " | ";
                }
            }
            original = original + System.getProperty("line.separator");
            if ((i+1)%3 == 0) {
                original = original + System.getProperty("line.separator");
            }
        }
        String s = original + newline + "Answer:" + newline + this.toString(answerGrid) + "Current:" + newline + this.toString(currentGrid);
        String candidates = "Candidates:" + newline;
        for (int i=0; i<ModelGrid.NUM_ROWS; i++) {
            for (int j=0; j<ModelGrid.NUM_COLS; j++) {
                candidates = candidates + "[ ";
                for (int k=1; k<9; k++) {
                    if (currentGrid.grid.get(i).get(j).hasCandidate(k)) {
                        candidates = candidates + k + " ";
                    }
                }
                candidates = candidates + "] ";
                if ((j+1)%3 == 0) {
                         candidates = candidates + " ";
                }
            }
            candidates = candidates + " ";
        }
        s = s + newline + candidates;
        try {
            PrintWriter print = new PrintWriter(save);
            print.write(s);
            print.close();
        } catch (FileNotFoundException e) {}
    }
 
    public void loadGame(File save) {
//      File save = new File(location);
        try {
            model.clearPuzzle();
            Scanner s = new Scanner(save);
            s.next();
            for (int i=0; i<ModelGrid.NUM_ROWS; i++) {
                for (int j=0; j<ModelGrid.NUM_COLS; j++) {
                        s.next();
                        if (s.hasNextInt()) {
                        currentGrid.grid.get(i).get(j).giveNumber(s.nextInt());
                        } else {
                                s.next();
                        }
                    if ((j+1)%3 == 0) {
                         s.next();
                    }
                }
            }
            s.next();
            for (int i=0; i<ModelGrid.NUM_ROWS; i++) {
                for (int j=0; j<ModelGrid.NUM_COLS; j++) {
                        s.next();
                        if (s.hasNextInt()) {
                        answerGrid.grid.get(i).get(j).giveNumber(s.nextInt());
                        } else {
                                s.next();
                        }
                    if ((j+1)%3 == 0) {
                         s.next();
                    }
                }
            }
            s.next();
            for (int i=0; i<ModelGrid.NUM_ROWS; i++) {
                for (int j=0; j<ModelGrid.NUM_COLS; j++) {
                        s.next();
                        if (s.hasNextInt()) {
                                if (currentGrid.grid.get(i).get(j).isGiven() == false) {
                            currentGrid.grid.get(i).get(j).setNumber(s.nextInt());
                                } else {
                                        s.next();
                                }
                        } else {
                                s.next();
                        }
                    if ((j+1)%3 == 0) {
                         s.next();
                    }
                }
            }
            s.next();
            for (int i=0; i<ModelGrid.NUM_ROWS; i++) {
                for (int j=0; j<ModelGrid.NUM_COLS; j++) {
                        if (s.next() == "[") {
                                s.skip("[");
                        }
                        if (s.next() == "]") {
                                s.skip("]");
                    }
                        while (s.hasNextInt()) {
                            currentGrid.grid.get(i).get(j).addCandidate(s.nextInt());
                        }
 
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
 
        } catch (NoSuchElementException e) {
       
        }
    }
   
   
    public void printCandidates() {
        for (int i=0; i<ModelGrid.NUM_ROWS; i++) {
            for (int j=0; j<ModelGrid.NUM_COLS; j++) {
                System.out.print(this.currentGrid.grid.get(i).get(j).getCandidates());
            }
        }
    }
}
