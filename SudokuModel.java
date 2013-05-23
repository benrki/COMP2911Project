import java.util.ArrayList;
import java.util.Random;

// To do: 
// rearrange order methods and maybe change names
// rethink where each method belongs.
// Undo/Redo
// Can remove a lot of the "checks" since I've implemented them in Cell

public class SudokuModel implements SudokuModelInterface{
    private Grid currentGrid;
    private Grid answerGrid;
    private ArrayList<String> history;
    
    public SudokuModel () {
        this.currentGrid = new Grid();
        this.answerGrid = new Grid();
        this.history = new ArrayList<String>();
    }
    
    //*****************************************
    //*** Creating/Generating phase methods ***
    //*****************************************
    /**
     * Generates a random puzzle (with numbers "missing") and stores the corresponding answer.
     */
    @Override
    public void generatePuzzle() {
        //http://zhangroup.aporc.org/images/files/Paper_3485.pdf
        //TO DO!!!!
        SudokuGenerator generator = new SudokuGenerator(this.currentGrid, this.answerGrid);
        generator.generate();
        
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
        if(1<=n && n<=9) {
            this.currentGrid.getCell(row, col).giveNumber(n);
            this.answerGrid.getCell(row, col).giveNumber(n);
        }
    } 
    
    @Override
    public void removeCellNumber(int row, int col) {
        this.currentGrid.getCell(row, col).removeNumber();
        this.answerGrid.getCell(row, col).removeNumber();
    }
    
    @Override
    public boolean isGridValid() {
        return this.currentGrid.isGridValid();
    }
    @Override
    public boolean isRowValid(int row){
        return this.currentGrid.isRowValid(row);
    }
    @Override
    public boolean isColumnValid(int column){
        return this.currentGrid.isColumnValid(column);
    }
    @Override
    public boolean isBoxValid(int box){
        return this.currentGrid.isBoxValid(box);
    }
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
                    if(j==0) {
                        i--;
                        j = 8;
                    } else {
                        j--;
                    }
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
    
    //*****************************************
    //********* Solving phase methods *********
    //*****************************************
    
    /**
     * Returns the number in the specified cell in the currentGrid.
     * @return the number in the specified cell in the currentGrid.
     */
    @Override
    public int getCellNumber(int row, int col) {
        return this.currentGrid.getCell(row, col).getNumber();
    }
    /**
     * Sets the number in the specified cell in the currentGrid as the specified number.
     */
    @Override
    public void setCellNumber(int row, int col, int n) {
        if(1<=n && n<=9) {
            this.currentGrid.getCell(row, col).setNumber(n); 
        }
    }
    @Override
    public void clearCellNumber(int row, int col) {
        this.currentGrid.getCell(row, col).clearNumber();
    }
    
    @Override
    public boolean hasCellCandidate(int row, int col, int n) {
        if(1<=n && n<=9) {
            return currentGrid.getCell(row, col).hasCandidate(n);
        }
        return false;
    }
    @Override
    public void addCellCandidate(int row, int col, int n) {
        if(1<=n && n<=9) {
            currentGrid.getCell(row, col).addCandidate(n);
        }
    }
    public void removeCellCandidate(int row, int col, int n) {
        if(1<=n && n<=9) {
            currentGrid.getCell(row, col).removeCandidate(n);
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

    public void revealRandom() {
        // NEED TO FIX, CURRENTLY INFINITELY LOOPS WHEN YOU TRY TO REVEAL WHEN BOARD IS FINISHED ALREADY
        Random r = new Random();
        int row;
        int col;
        boolean revealed = false;
        while(!revealed) {
            row = r.nextInt(9);
            col = r.nextInt(9);
            if(this.currentGrid.getCell(row, col).getNumber()==Grid.EMPTY) {
                this.revealCell(row, col);
                revealed = true;
            }
        }
    }    
    public void revealCell(int row, int col) {
        this.currentGrid.getCell(row, col).setNumber(this.answerGrid.getCell(row, col).getNumber());
    }
    
    public void undoMove() {
        
    }
    public void redoMove() {
        
    }
    
    @Override
    public void saveGame(String location, String name) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void loadGame() {
        // TODO Auto-generated method stub
        
    }
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
    
    // FOR TESTING ONLY METHODS
    public void printCurrentGrid() {
        this.currentGrid.printGrid();
    }
    public void printAnswerGrid() {
        this.answerGrid.printGrid();
    }

}