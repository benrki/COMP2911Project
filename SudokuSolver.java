import java.util.ArrayList;

// To do: 
// rearrange order methods and maybe change names
// rethink where each method belongs.

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
    public boolean findAnswers() {   // I want to change this to findSolution later.
        int i = 0, j = 0;
        boolean finished = true;
        while(finished) {
            int potential = answerGrid.getCell(i,j).getNumber() + 1;
            // If exhausted all potential numbers, set cell to 0 then backtrack 
            if(potential > 9) {
                answerGrid.getCell(i, j).setNumber(0);
                boolean backtracked = false;
                // Backtracks to the previous ungiven cell.
                while(!backtracked || answerGrid.getCell(i, j).isGiven()) {
                    // Make this better later
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
            // Else, set potential to cell.
            } else {
                answerGrid.getCell(i, j).setNumber(potential);
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
            if(currentGrid.getCell(row, i).getNumber()!=(answerGrid.getCell(row, i).getNumber())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isColumnCorrect(int col) {
        for(int i=0; i<Grid.NUM_ROWS; i++) {
            if(currentGrid.getCell(i, col).getNumber()!=(answerGrid.getCell(i, col).getNumber())) {
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
                if(currentGrid.getCell(i, j).getNumber()!=(answerGrid.getCell(i, j).getNumber())) {
                    return false;
                }
            }
        }
        return true;
    }
}
