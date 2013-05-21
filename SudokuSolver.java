import java.util.ArrayList;

public class SudokuSolver implements SudokuSolverInterface{
    private Grid currentGrid;
    private Grid answerGrid;
    
    public SudokuSolver () {
        this.currentGrid = new Grid();
        this.answerGrid = new Grid();
    }
    
    /** Returns <tt>true</tt> if solution is found, <tt>false</tt> otherwise. Also stores the solution if found.
     * @return  <tt>true</tt> if solution is found, <tt>false</tt> otherwise.
     */
    @Override
    public boolean findAnswers() {   
        int i = 0;
        int j = 0;
        boolean finished = true;
        while(finished) {
            int potential = answerGrid.getCell(i,j).getNumber() + 1;
            // Exhausted all potential numbers, set 0 then backtrack 
            if(potential > 9) {
                answerGrid.getCell(i, j).setNumber(0);
                boolean backtracked = false;
                while(!backtracked || answerGrid.getCell(i, j).isGiven()) {
                    // Fix this later!!!
                    if(j==0) {
                        i--;
                    }
                    j = Math.abs((j-1)%9);
                    // Backtracked past the start of the grid (can't solve every cell)
                    if(i<0) {
                        return false;
                    }
                    backtracked = true;
                }
            } else {
                answerGrid.getCell(i, j).setNumber(potential);
                boolean valid = answerGrid.isCellValid(i,j);
                if(valid) {
                    boolean advanced = false;
                    while(!advanced || answerGrid.getCell(i, j).isGiven()) {
                        i = i + j/8;
                        j = (j+1)%9;
                        advanced = true;
                        if (i>8) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Sets all the numbers in the currentGrid to the numbers in the answerGrid.
     */
    @Override
    public void solveGrid() {
        for(int i=0; i<Grid.NUM_ROWS; i++) {
            for(int j=0; j<Grid.NUM_COLS; j++) {
                currentGrid.getCell(i, j).setNumber(answerGrid.getCell(i, j).getNumber());
            }
        }
    }

    /**
     * Returns the number in the specified cell in the currentGrid.
     * @return the number in the specified cell in the currentGrid.
     */
    @Override
    public int getCurrentCellNumber(int row, int col) {
        return this.currentGrid.getCell(row, col).getNumber();
    }

    /**
     * Sets the number in the specified cell in the currentGrid as the specified number.
     */
    @Override
    public void setCurrentCellNumber(int row, int col, int n) {
        this.currentGrid.getCell(row, col).setNumber(n); 
    }
    
    /**
     * Sets the number in the specified cell in the currentGrid as the specified number. 
     * (Different to setCurrentCellNumber as it prevents the number from being changed in the future).
     * Should be used when initialising the board.
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @param n The number to set the cell as.
     */
    @Override
    public void giveCellNumber(int row, int col, int n) {
        this.currentGrid.getCell(row, col).setNumber(n);
        this.currentGrid.getCell(row, col).setGiven(true);
        this.answerGrid.getCell(row, col).setNumber(n);
        this.answerGrid.getCell(row, col).setGiven(true);
    }
    
    @Override
    public boolean currentCellHasCandidate(int row, int col, int n) {
        return currentGrid.getCell(row, col).hasCandidate(n);
    }

    @Override
    public void addCurrentCellCandidates(int row, int col, int n) {
        currentGrid.getCell(row, col).addCandidate(n);
    }

    @Override
    public boolean isCurrentCellValid(int row, int col) {
        return this.currentGrid.isCellValid(row, col);
    }
    
    public void printCurrentGrid() {
        this.currentGrid.printGrid();
    }
    public void printAnswerGrid() {
        this.answerGrid.printGrid();
    }

    @Override
    public boolean isCurrentCellCorrect(int row, int col) {
        return this.currentGrid.getCell(row, col).isCorrect(this.answerGrid.getCell(row, col));
    }
    
    
    //public int getCurrentCellNumber()
    
    // (Make blank grid)
    // Input External Puzzle
    // (Every input has given==true)
    // Play
    // (Checks if hasSolution, returns grid)
    // 
    
    
    
    
    
    // void startTime()
    // void getTime()
    // void undo()
    // void redo()
}
