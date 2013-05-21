public class SudokuSolver implements SudokuSolverInterface{
    private Grid currentGrid;
    private Grid answerGrid;
    
    public SudokuSolver () {
        this.current = new Grid();
        this.answer = new Grid();
    }
    
    public boolean findAnswers() {   
        int i=0;
        int j=0;
        boolean hasAnswer = true;
        while(hasAnswer) {
            // Backtracked past the start of the grid
            if(i<0) {
                hasAnswer = false;
            }
            int potential = answerGrid.getCell(i,j).getNumber() + 1;
            // Exhausted all potential numbers
            if(potential > 9) {
                // Make cell 0
                //Backtrack
            } else {
                answerGrid.getCell(i, j).setNumber(potential);
                boolean valid = answerGrid.isCellValid(answerGrid.getCell(i,j));
                if(valid) {
                    // advance to next cell
                }
            }
            // Visit empty cells in order
            // Checks if number+1 <= 9
                // Yes, Attempts to fill the cell with number+1
                // Check if valid, 
                    // Yes, next cell
                    // No, stay in cell (number+1)
            
                // No, backtracks make cell 0
        
        }
        return hasAnswer;
    }
    
    public void solveGrid() {
        
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
    /*
    public void findAnswers() {   
        int i=0;
        int j=0;
        boolean finished = false;
        while(!finished) {
            Cell current = grid.get(i).get(j);
            if(!current.isGiven()) {
                boolean advance = false;
                int k = current.getAnswer();
                while(!advance) {
                    k++;
                    if(k>9) {
                        current.setAnswer(0);
                        while(advance==false) {
                            Cell c = this.prevCell(this.grid.get(i).get(j));
                            i = c.getRow();
                            j = c.getColumn();
                            if(!this.grid.get(i).get(j).isGiven()) {
                                advance = true;
                            }   
                        }
                    } else {
                        current.setAnswer(k);
                        // No violations, go next ungiven
                        if(this.isCellValid(current)) {
                            advance = true;
                            Cell c = this.nextCell(current);
                            if(c!=null) {
                                i = c.getRow();
                                j = c.getColumn();
                            } else {
                                return;
                            }
                        }
                    }
                }
            } else {
                Cell c = this.nextCell(current);
                if(c!=null) {
                    i = c.getRow();
                    j = c.getColumn();
                } else {
                    return;
                }
            }
        }
    }
    
    public void solveGrid() {
        for(int i=0; i<NUM_ROWS; i++) {
            for(int j=0; j<NUM_COLS; j++) {
                int answer = this.grid.get(i).get(j).getAnswer();
                this.grid.get(i).get(j).setCurrent(answer);
            }
        }
    }
    
    public Cell nextCell(Cell c) {
        int row = c.getRow();
        int col = c.getColumn();
        int nextRow = row;
        int nextCol = col+1;
        if(col==8) {
            nextCol = 0;
            nextRow = row+1;
        }
        if(nextRow>8 || nextCol >8) {
            return null;
        } 
        return this.grid.get(nextRow).get(nextCol);
    }
    
    public Cell prevCell (Cell c) {
        int row = c.getRow();
        int col = c.getColumn();
        int prevRow = row;
        int prevCol = col-1;
        if(col==0) {
            prevCol = 8;
            prevRow = row-1;
        }
        if(prevRow>8 || prevCol >8) {
            return null;
        } 
        return this.grid.get(prevRow).get(prevCol);
    }*/
}
