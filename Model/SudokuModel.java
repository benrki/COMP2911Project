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
public class SudokuModel implements SudokuModelInterface{
    
    private Grid currentGrid;
    private Grid answerGrid;
    private Stack<String> undoStack;
    private Stack<String> redoStack;
    
    public SudokuModel () {
        this.currentGrid = new Grid();
        this.answerGrid = new Grid();
        this.undoStack = new Stack<String>();
        this.redoStack = new Stack<String>();
    }
    
    //*****************************************
    //*** Creating/Generating phase methods ***
    //*****************************************
    
    /**
     * Generates a random puzzle (with numbers "missing") and stores the corresponding answer.
     */
    /*@Override
    public void generatePuzzle() {
        SudokuGenerator generator = new SudokuGenerator(this.currentGrid, this.answerGrid);
        generator.generate();
        this.undoStack.clear();
        this.redoStack.clear();
        
    }*/
    
    /**
     * Generates an easy puzzle (with numbers "missing").
     */
    @Override
    public void generateEasyPuzzle() {
    	SudokuGenerator generator = new EasyGenerator(this.currentGrid, this.answerGrid);
        generator.generate();
        this.undoStack.clear();
        this.redoStack.clear();
    }
    
    /**
     * Generates an intermediate puzzle (with numbers "missing").
     */
    @Override
    public void generateIntermediatePuzzle() {
    	SudokuGenerator generator = new IntermediateGenerator(this.currentGrid, this.answerGrid);
        generator.generate();
        this.undoStack.clear();
        this.redoStack.clear();
    }
    
    /**
     * Generates a hard puzzle (with numbers "missing").
     */
    @Override
    public void generateHardPuzzle() {
    	SudokuGenerator generator = new HardGenerator(this.currentGrid, this.answerGrid);
        generator.generate();
        this.undoStack.clear();
        this.redoStack.clear();
    }
    
    @Override
    public void clearPuzzle() {
        this.currentGrid = new Grid();
        this.answerGrid = new Grid();
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
        if((Cell.MIN_NUM <= n) && (n <= Cell.MAX_NUM)) {
            this.currentGrid.getCell(row, col).giveNumber(n);
            this.answerGrid.getCell(row, col).giveNumber(n);
            this.undoStack.push("giveCellNumber " + row + " " + col + " " + n);
            this.redoStack.clear();
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
        if(n!=Cell.EMPTY) {
            this.currentGrid.getCell(row, col).removeNumber();
            this.answerGrid.getCell(row, col).removeNumber();
            this.undoStack.push("removeCellNumber " + row + " " + col + " " + n);
            this.redoStack.clear();
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
        int i = 0, j = 0;
        boolean finished = true;
        while(finished) {
            int potential = answerGrid.getCell(i,j).getNumber() + 1;
            // If exhausted all potential numbers, set cell to 0 then backtrack 
            if(potential > 9) {
                this.removeCellNumber(i, j);
                boolean backtracked = false;
                // Backtracks to the previous ungiven cell.
                while(!backtracked || answerGrid.getCell(i, j).isGiven()) {
                    // Make this better later
                    i = i + (j-9)/9;
                    j--;
                    // Backtracked past the start of the grid (can't solve every cell)
                    if(i<0) {
                        return false;
                    }
                    backtracked = true;
                }
            // Else, set potential to cell.
            } else {
                answerGrid.getCell(i, j).giveNumber(potential);
                boolean valid = answerGrid.isCellValid(i,j);
                // If valid, advances to the next ungiven cell.
                if(valid) {
                    boolean advanced = false;
                    while(!advanced || answerGrid.getCell(i, j).isGiven()) {
                        i = i + j/8;
                        j = (j+1)%9;
                        advanced = true;
                        // Advanced past the end of the grid (found solution)
                        if (i>8) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
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
        if((Cell.MIN_NUM <= n) && (n <= Cell.MAX_NUM)) {
            this.currentGrid.getCell(row, col).setNumber(n); 
            this.undoStack.push("setCellNumber " + row + " " + col + " " + n);
            this.redoStack.clear();
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
        if(n!=Cell.EMPTY) {
            this.currentGrid.getCell(row, col).clearNumber();
            this.undoStack.push("clearCellNumber " + row + " " + col + " " + n);
            this.redoStack.clear();
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
        if((Cell.MIN_NUM <= n) && (n <= Cell.MAX_NUM)) {
            currentGrid.getCell(row, col).addCandidate(n);
        }
    }
    
    
    public void removeCellCandidate(int row, int col, int n) {
        if((Cell.MIN_NUM <= n) && (n <= Cell.MAX_NUM)) {
            currentGrid.getCell(row, col).removeCandidate(n);
        }
    }
    @Override
    public boolean hasCellCandidate(int row, int col, int n) {
        if((Cell.MIN_NUM <= n) && (n <= Cell.MAX_NUM)) {
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
        for (int i=0; i<Grid.NUM_ROWS; i++) {
            for (int j=0; j<Grid.NUM_COLS; j++) {
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
        if(this.currentGrid.getCell(row, col).getNumber()==Cell.EMPTY) {
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
        for(int i=0; i<Grid.NUM_COLS; i++) {
            if(!isCellCorrect(row, i)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean isColumnCorrect(int col) {
        for(int i=0; i<Grid.NUM_ROWS; i++) {
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
                if(this.currentGrid.getCell(i, j).getNumber()==Cell.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public void revealRandom() {
        // NEED TO FIX, CURRENTLY INFINITELY LOOPS WHEN YOU TRY TO REVEAL WHEN BOARD IS FINISHED ALREADY
        if(!isGridFilled()) {
            return;
        }
        Random r = new Random();
        int row;
        int col;
        boolean revealed = false;
        while(!revealed) {
            row = r.nextInt(9);
            col = r.nextInt(9);
            if(this.currentGrid.getCell(row, col).getNumber()==Cell.EMPTY) {
                this.revealCell(row, col);
                revealed = true;
            }
        }
    }    
    @Override
    public void revealCell(int row, int col) {
        this.currentGrid.getCell(row, col).setNumber(this.answerGrid.getCell(row, col).getNumber());
    }
    @Override
    public void undoMove() {
        if(undoStack.isEmpty()) {
            return;
        }
        String move = undoStack.pop();
        Scanner s = new Scanner(move);
        String command = s.next();
        Stack<String> tmp = this.redoStack;
        this.redoStack = new Stack<String>();
        if(command.equals("giveCellNumber")) {
            removeCellNumber(s.nextInt(), s.nextInt());
        } else if (command.equals("removeCellNumber")) {
            giveCellNumber(s.nextInt(), s.nextInt(), s.nextInt());
        } else if (command.equals("setCellNumber")) {
            clearCellNumber(s.nextInt(), s.nextInt());
        } else if (command.equals("clearCellNumber")) {
            setCellNumber(s.nextInt(), s.nextInt(), s.nextInt());
        }
        s.close();
        this.redoStack = tmp;
        this.redoStack.push(undoStack.pop());
        
    }
    @Override
    public void redoMove() {
        if(redoStack.isEmpty()) {
            return;
        }
        String move = redoStack.pop();
        Scanner s = new Scanner(move);
        String command = s.next();
        Stack<String> tmp = this.redoStack;
        this.redoStack = new Stack<String>();
        if(command.equals("giveCellNumber")) {
            removeCellNumber(s.nextInt(), s.nextInt());
        } else if (command.equals("removeCellNumber")) {
            giveCellNumber(s.nextInt(), s.nextInt(), s.nextInt());
        } else if (command.equals("setCellNumber")) {
            clearCellNumber(s.nextInt(), s.nextInt());
        } else if (command.equals("clearCellNumber")) {
            setCellNumber(s.nextInt(), s.nextInt(), s.nextInt());
        }
        s.close();
        this.redoStack = tmp;
    }
    
    @Override
    public void saveGame(String location, String name) {
        String newline = System.getProperty("line.separator");
    	File parentDir = new File(location);
    	parentDir.mkdir();
    	File file = new File(parentDir, name + ".txt");
    	try {
			file.createNewFile();
		} catch (IOException e) {
			
		}
    	String original = "Original:" + newline;
    	for (int i=0; i<Grid.NUM_ROWS; i++) {
    		for (int j=0; j<Grid.NUM_COLS; j++) {
    			if (currentGrid.grid.get(i).get(j).isGiven() == true) {
    				original = original + currentGrid.grid.get(i).get(j).getNumber() + " ";
    			} else if (currentGrid.grid.get(i).get(j).isGiven() == false) {
    				original = original + "- ";
    			}
    		}
    		original = original + newline;
    	}
    	String save = original + newline + "Current:" + newline + currentGrid.toString();
    	try {
			PrintWriter print = new PrintWriter(file);
			print.write(save);
			print.close();
		} catch (FileNotFoundException e) {}
    }

    @Override
    public void loadGame(String location) {
    	File file = new File(location);
    	try {
			Scanner save = new Scanner(file);
			for (int i=0; i<Grid.NUM_ROWS; i++) {
	    		for (int j=0; j<Grid.NUM_COLS; j++) {
	    			giveCellNumber(i, j, save.nextInt());
	    		}
	    	}
			for (int i=0; i<Grid.NUM_ROWS; i++) {
	    		for (int j=0; j<Grid.NUM_COLS; j++) {
	    			if (currentGrid.grid.get(i).get(j).isGiven() == false) {
	    				setCellNumber(i, j, save.nextInt());
	    			} else {
	    				giveCellNumber(i, j, save.nextInt());
	    			}
	    		}
	    	}
			save.close();
		} catch (FileNotFoundException e) {
			
		} catch (NoSuchElementException e) {
    	
		}
    }
    
    /*private void stringToGrid(String grid) {
        Scanner s = new Scanner(grid);
        for (int i=0; i<Grid.NUM_ROWS; i++) {
            for (int j=0; j<Grid.NUM_COLS; j++) {
                giveCellNumber(i, j, s.nextInt());
            }
        }
        for (int i=0; i<Grid.NUM_ROWS; i++) {
            for (int j=0; j<Grid.NUM_COLS; j++) {
                if (currentGrid.grid.get(i).get(j).isGiven() == false) {
                    setCellNumber(i, j, s.nextInt());
                } else {
                    giveCellNumber(i, j, s.nextInt());
                }
            }
        }
        s.close();
    }*/
    
    //*****************************************
    //********** Reveal phase methods *********
    //*****************************************
    /**
     * Sets all the numbers in the currentGrid to the numbers in the answerGrid.
     */
    @Override
    public void revealSolution() {
        for(int i=0; i<Grid.NUM_ROWS; i++) {
            for(int j=0; j<Grid.NUM_COLS; j++) {
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

}
