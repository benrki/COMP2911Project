import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

// To do: 
// rearrange order methods and maybe change names
// rethink where each method belongs.
// Undo/Redo
// Can remove a lot of the "checks" since I've implemented them in Cell
// Potential bug: Generating then pressing undo will "ungive" (Potential fix: add undoStack.clear to hasAnswer (right before it returns true)

// Potential bug: Undo moves that haven't gone through.
public class SudokuModel implements SudokuModelInterface{
    
    private ModelGrid currentGrid;
    private ModelGrid answerGrid;
    private ModelUndoRedo undoRedo;
    private ModelTimer timer;
    private String generator;
    
    public SudokuModel () {
        this.currentGrid = new ModelGrid();
        this.answerGrid = new ModelGrid();
        this.undoRedo = new ModelUndoRedo(this);
        this.timer = new ModelStopwatch(0);
        this.generator = "clearPuzzle";
    }
    
    //*****************************************
    //*** Creating/Generating phase methods ***
    //*****************************************

    /**
     * Generates an easy puzzle (with numbers "missing").
     */
    @Override
    public void generateEasyPuzzle() {
        ModelGenerator generator = new ModelEasyGenerator(this.currentGrid, this.answerGrid);
        generator.generate();
        undoRedo.generateCalled();
        this.generator = "genereateEasyPuzzle";
    }
    
    /**
     * Generates an intermediate puzzle (with numbers "missing").
     */
    @Override
    public void generateIntermediatePuzzle() {
        ModelGenerator generator = new ModelIntermediateGenerator(this.currentGrid, this.answerGrid);
        generator.generate();
        undoRedo.generateCalled();
        this.generator = "genereateIntermediatePuzzle";
    }
    
    /**
     * Generates a hard puzzle (with numbers "missing").
     */
    @Override
    public void generateHardPuzzle() {
        ModelGenerator generator = new ModelHardGenerator(this.currentGrid, this.answerGrid);
        generator.generate();
        undoRedo.generateCalled();
        this.generator = "genereateHardPuzzle";
    }
    
    @Override
    public void clearPuzzle() {
        this.currentGrid = new ModelGrid();
        this.answerGrid = new ModelGrid();
        undoRedo.generateCalled();
        this.generator = "clearPuzzle";
    }
    
    
    @Override
    public void regenerate() {
      if(this.generator.equals("genereateEasyPuzzle")) {
          this.generateEasyPuzzle();
      } else if(this.generator.equals("genereateIntermediatePuzzle")) {
          this.generateIntermediatePuzzle();
      } else if(this.generator.equals("genereateHardPuzzle")) {
          this.generateHardPuzzle();
      } else if(this.generator.equals("clearPuzzle")) {
          this.clearPuzzle();
      }
    }
    
    /**
     * Sets the number in the specified cell in the grid as the specified number. 
     * (Different to setCellNumber as it makes the cell a "given", preventing it from being changed by setCellNumber and clearCellNumber.)
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @param n The number to set the cell as.
     */
    @Override
    public void giveCellNumber(int row, int col, int n) {
        if((ModelCell.MIN_NUM <= n) && (n <= ModelCell.MAX_NUM)) {
            undoRedo.giveCellNumberCalled(row, col, n, this.currentGrid.getCell(row, col).getNumber());
            this.currentGrid.getCell(row, col).giveNumber(n);
            this.answerGrid.getCell(row, col).giveNumber(n);
        }
    } 
    
    /**
     * Removes the number in the specified cell in the grid.
     * (Differs from clearCellNumber as it can clear "givens").
     * @param row
     * @param col
     */
    @Override
    public void removeCellNumber(int row, int col) {
        int n = this.answerGrid.getCell(row, col).getNumber();
        if(n!=ModelCell.EMPTY) {
            this.currentGrid.getCell(row, col).removeNumber();
            this.answerGrid.getCell(row, col).removeNumber();
            undoRedo.removeCellNumberCalled(row, col, n);
        }
    }
    
    /**
     * Returns <tt>true</tt> if the grid conforms to row, column and box constraints, <tt>false</tt> otherwise.
     * @return <tt>true</tt> if the grid conforms to row, column and box constraints, <tt>false</tt> otherwise.
     */
    @Override
    public boolean isGridValid() {
        return this.currentGrid.isGridValid();
    }
    
    /**
     * Returns <tt>true</tt> if the specified row conforms to row constraints, <tt>false</tt> otherwise.
     * @param row
     * @return <tt>true</tt> if the specified row conforms to row constraints, <tt>false</tt> otherwise.
     */
    @Override
    public boolean isRowValid(int row){
        return this.currentGrid.isRowValid(row);
    }

    /**
     * Returns <tt>true</tt> if the specified column conforms to column constraints, <tt>false</tt> otherwise.
     * @param column
     * @return <tt>true</tt> if the specified column conforms to column constraints, <tt>false</tt> otherwise.
     */
    @Override
    public boolean isColumnValid(int column){
        return this.currentGrid.isColumnValid(column);
    }
    
    /**
     * Returns <tt>true</tt> if the specified box conforms to box constraints, <tt>false</tt> otherwise.
     * @param box
     * @return <tt>true</tt> if the specified box conforms to box constraints, <tt>false</tt> otherwise.
     */
    @Override
    public boolean isBoxValid(int box){
        return this.currentGrid.isBoxValid(box);
    }
    
    /**
     * Returns <tt>true</tt> if the specified cell conforms to row, column and box constraints, <tt>false</tt> otherwise.
     * @param row
     * @param col
     * @return <tt>true</tt> if the specified cell conforms to row, column and box constraints, <tt>false</tt> otherwise.
     */
    @Override
    public boolean isCellValid(int row, int col) {
        return this.currentGrid.isCellValid(row, col);
    }
    
    /** Returns <tt>true</tt> if solution is found, <tt>false</tt> otherwise. Also stores the solution if found.
     * @return  <tt>true</tt> if solution is found, <tt>false</tt> otherwise.
     */
    @Override
    // Recursion this later
    public boolean findSolution() {
        ModelSolver solver = new ModelSolver(this, this.currentGrid, this.answerGrid);
        return solver.findSolution();
    }
    
    //*****************************************
    //********* Solving phase methods *********
    //*****************************************
    
    /**
     * Returns the number in the specified cell in the currentGrid.
     * @param row
     * @param col
     * @return the number in the specified cell in the currentGrid.
     */
    @Override
    public int getCellNumber(int row, int col) {
        return this.currentGrid.getCell(row, col).getNumber();
    }
    /**
     * Sets the number in the specified cell in the currentGrid as the specified number.
     * @param row
     * @param col
     * @param n
     */
    @Override
    public void setCellNumber(int row, int col, int n) {
        if((ModelCell.MIN_NUM <= n) && (n <= ModelCell.MAX_NUM) && !this.currentGrid.getCell(row, col).isGiven()) {
            undoRedo.setCellNumberCalled(row, col, n, this.currentGrid.getCell(row, col).getNumber());
            this.currentGrid.getCell(row, col).setNumber(n); 
        }
    }
    
    /**
     * Clears the specified cell. (Sets it to blank.)
     * @param row
     * @param col
     */
    @Override
    public void clearCellNumber(int row, int col) {
        int n = this.currentGrid.getCell(row, col).getNumber();
        if(n!=ModelCell.EMPTY) {
            this.currentGrid.getCell(row, col).clearNumber();
            undoRedo.clearCellNumberCalled(row, col, n);
        }
    }
    
    /**
     * Adds specified candidate to the specified cell.
     * @param row
     * @param col
     * @param n
     */
    @Override
    public void addCellCandidate(int row, int col, int n) {
        if((ModelCell.MIN_NUM <= n) && (n <= ModelCell.MAX_NUM)) {
            currentGrid.getCell(row, col).addCandidate(n);
        }
    }
    
    
    public void removeCellCandidate(int row, int col, int n) {
        if((ModelCell.MIN_NUM <= n) && (n <= ModelCell.MAX_NUM)) {
            currentGrid.getCell(row, col).removeCandidate(n);
        }
    }
    @Override
    public boolean hasCellCandidate(int row, int col, int n) {
        if((ModelCell.MIN_NUM <= n) && (n <= ModelCell.MAX_NUM)) {
            return currentGrid.getCell(row, col).hasCandidate(n);
        }
        return false;
    }
    @Override
    public ArrayList<Integer> getCandidates(int row, int col) {
        return currentGrid.getCell(row, col).getCandidates();
    }
    
    @Override
    public void addAllCellCandidates(int row, int col) {
        if(currentGrid.getCell(row, col).isGiven()) {
            return;
        }
        // Clear candidates
        clearCellCandidates(row, col);
        int tmp = currentGrid.getCell(row, col).getNumber();
        // Check each number
        for (int i=1; i<=9; i++) {
            currentGrid.getCell(row, col).setNumber(i);
            if(this.currentGrid.isCellValid(row, col)) {
                currentGrid.getCell(row, col).addCandidate(i);
            }
        }
        currentGrid.getCell(row, col).setNumber(tmp);
        
    }
    
    @Override
    public void addAllCandidates() {
        for (int i=0; i<ModelGrid.NUM_ROWS; i++) {
            for (int j=0; j<ModelGrid.NUM_COLS; j++) {
                addAllCellCandidates(i, j);
            }
        }
        
    }
    public void clearCellCandidates (int row, int col) {
        for(int i=1; i<=9; i++) {
            currentGrid.getCell(row, col).removeCandidate(i);
        }
    }
    public void clearAllCandidates () {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                for(int k=1; k<=9; k++) {
                    currentGrid.getCell(i, j).removeCandidate(k);
                }
            }
        }
    }

    @Override
    public boolean isCellCorrect(int row, int col) {
        if(this.currentGrid.getCell(row, col).getNumber()==ModelCell.EMPTY) {
            return true;
        }
        return (this.currentGrid.getCell(row, col).getNumber()==this.answerGrid.getCell(row, col).getNumber());
    }
    @Override
    public boolean isGridCorrect() {
        for(int i=0; i<9; i++) {
            if(!isRowCorrect(i) || !isColumnCorrect(i) || !isBoxCorrect(i) ) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean isRowCorrect(int row) {
        for(int i=0; i<ModelGrid.NUM_COLS; i++) {
            if(!isCellCorrect(row, i)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean isColumnCorrect(int col) {
        for(int i=0; i<ModelGrid.NUM_ROWS; i++) {
            if(!isCellCorrect(i, col)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean isBoxCorrect(int box) {
        int row = (box/3)*3; // Row of top-left cell of box
        int col = (box%3)*3; // Col of top-left cell of box
        for(int i=row; i<row+3; i++) {
            for(int j=col; j<col+3; j++) {
                if(!isCellCorrect(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    /*
    @Override
	public boolean isSudokuFinished() {
    	for(int i=0; i<9; i++) {
    		for(int j=0; j<9; j++) {
    			if(this.currentGrid.getCell(i, j).getNumber()!=this.answerGrid.getCell(i, j).getNumber()) {
    				return false;
    			}
    		}
    	}
        return true;
	}*/

    @Override
    public boolean isGridFilled() {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                if(this.currentGrid.getCell(i, j).getNumber()==ModelCell.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public Position revealRandom() {
        if(isGridFilled()) {
            //return;
            return null;
        }
        Random r = new Random();
        int row = r.nextInt(9);
        int col = r.nextInt(9);
        boolean revealed = false;
        while(!revealed) {
            if(this.currentGrid.getCell(row, col).getNumber()==ModelCell.EMPTY) {
                this.revealCell(row, col);
                revealed = true;
            } else {
                row = r.nextInt(9);
                col = r.nextInt(9);
            }
        }
        return new Position(row, col);
    }    
    @Override
    public void revealCell(int row, int col) {
        this.currentGrid.getCell(row, col).setNumber(this.answerGrid.getCell(row, col).getNumber());
    }
    @Override
    public Position undoMove() {
        return undoRedo.undo();
    }
    @Override
    public Position redoMove() {
        return undoRedo.redo();
    }
    
    
     @Override
     public boolean canUndo() {
        return undoRedo.canRedo(); 
     }
     
     @Override
     public boolean canRedo() {
        return undoRedo.canRedo();
     }
     
    @Override
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
                if (currentGrid.grid.get(i).get(j).isGiven() == true) {
                    original = original + "|" + currentGrid.grid.get(i).get(j).getNumber();
                } else if (currentGrid.grid.get(i).get(j).isGiven() == false) {
                    original = original + "|-";
                }
                if ((j+1)%3 == 0) {
                    original = original + "| ";
                }
            }
            original = original + newline;
        }
        String s = original + newline + "Current:" + newline + currentGrid.toString();
        try {
            PrintWriter print = new PrintWriter(save);
            print.write(s);
            print.close();
        } catch (FileNotFoundException e) {}
    }

    @Override
    public void loadGame(File save) {
//      File save = new File(location);
        try {
            Scanner s = new Scanner(save);
            for (int i=0; i<ModelGrid.NUM_ROWS; i++) {
                for (int j=0; j<ModelGrid.NUM_COLS; j++) {
                    giveCellNumber(i, j, s.nextInt());
                }
            }
            for (int i=0; i<ModelGrid.NUM_ROWS; i++) {
                for (int j=0; j<ModelGrid.NUM_COLS; j++) {
                    if (currentGrid.grid.get(i).get(j).isGiven() == false) {
                        setCellNumber(i, j, s.nextInt());
                    } else {
                        giveCellNumber(i, j, s.nextInt());
                    }
                }
            }
            s.close();
        } catch (FileNotFoundException e) {

        } catch (NoSuchElementException e) {
        
        }
    }
    
    //*****************************************
    //********** Reveal phase methods *********
    //*****************************************
    /**
     * Sets all the numbers in the currentGrid to the numbers in the answerGrid.
     */
    @Override
    public void revealSolution() {
        for(int i=0; i<ModelGrid.NUM_ROWS; i++) {
            for(int j=0; j<ModelGrid.NUM_COLS; j++) {
                this.revealCell(i, j);
            }
        }
    }
    
    @Override
    public boolean isCellGiven(int row, int col) {
        return this.currentGrid.getCell(row, col).isGiven();
    }
    
    // FOR TESTING ONLY METHODS
    public void printCurrentGrid() {
        this.currentGrid.printGrid();
    }
    public void printAnswerGrid() {
        this.answerGrid.printGrid();
    }

    @Override
    public void startStopwatch(long startingTime) {
        this.timer = new ModelStopwatch(startingTime);
    }

    @Override
    public void startCountdown(long remainingTime) {
       this.timer = new ModelCountdown(remainingTime);
    }
    
    @Override
    public long getTime() {
        return this.timer.getTimer();
    }

    @Override
    public void pauseTime() {
       this.timer.pauseTimer();  
    }

    @Override
    public void unpauseTime() {
       this.timer.unpauseTimer();
    }

    @Override
    public void loadHighScores(File highscore) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void saveHighScores(File highscore) {
        // TODO Auto-generated method stub       
    }

}
