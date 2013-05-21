public class SudokuSolver implements SudokuSolverInterface{
    private Grid currentGrid;
    private Grid answerGrid;
    
    public SudokuSolver () {
        this.currentGrid = new Grid();
        this.answerGrid = new Grid();
    }
    
    public boolean findAnswers() {   
        int i=0;
        int j=0;
        boolean hasSolution = true;
        while(hasSolution) {
            // Backtracked past the start of the grid (can't solve every cell)
            if(i<0) {
                hasSolution = false;
            // Advance past the end of the grid (solved every cell)
            } else if (i>8) {
                return true;
            }
            int potential = answerGrid.getCell(i,j).getNumber() + 1;
            // Exhausted all potential numbers, set 0 then backtrack 
            if(potential > 9) {
                answerGrid.getCell(i, j).setNumber(0);
                boolean backtracked = false;
                while(!backtracked || !answerGrid.getCell(i, j).isGiven()) {
                    // Fix this later!!!
                    if(j==0) {
                        i--;
                        j=8;
                    } else {
                        j--;
                    }
                    backtracked = true;
                }
            } else {
                answerGrid.getCell(i, j).setNumber(potential);
                boolean valid = answerGrid.isCellValid(answerGrid.getCell(i,j));
                if(valid) {
                    i = i + j/9;
                    j = (j+1)%9;
                }
            }
        }
        return hasSolution;
    }
    
    /**
     * Sets all the numbers in the currentGrid to the numbers in the answerGrid.
     */
    public void solveGrid() {
        for(int i=0; i<Grid.NUM_ROWS; i++) {
            for(int j=0; j<Grid.NUM_COLS; j++) {
                currentGrid.getCell(i, j).setNumber(answerGrid.getCell(i, j).getNumber());
            }
        }
    }
    
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
