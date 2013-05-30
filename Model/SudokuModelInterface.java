import java.util.ArrayList;

public interface SudokuModelInterface {
    //*****************************************
    //*** Creating/Generating phase methods ***
    //*****************************************
    
    // ** PROGRAM GENERATING METHODS **
    // Generates a random puzzle (and reveals a set number of Cells in random positions)
    public void generateEasyPuzzle();
    public void generateIntermediatePuzzle();
    public void generateHardPuzzle();
    
    // *** USER GENERATING METHODS ***
    // Clears the puzzle grid.
    public void clearPuzzle();
    // Sets the number in the specified cell in the currentGrid as the specified number. 
    // Makes this cell a "given" cell. (Different to setCellNumber).
    public void giveCellNumber(int row, int col, int n);
    // Remove the number in the specified cell. 
    public void removeCellNumber(int row, int col);
    
    // Self explanatory (Validity is to do with row, column, box constraints)
    public boolean isGridValid();
    public boolean isRowValid(int row);
    public boolean isColumnValid(int col);
    public boolean isBoxValid(int box);
    public boolean isCellValid(int row, int col);
    
    // Finds the solution for current Grid.
    // Returns true if it has a solution, false otherwise. 
    // (What I planned was, you can't go to the "next phase" if this returns false)
    public boolean findSolution();
    
    
    //*****************************************
    //********* Solving phase methods *********
    //*****************************************
    
    // Gets the number of the specified Cell.
    public int getCellNumber(int row, int col);
    // Sets the number of the specified Cell.
    public void setCellNumber(int row, int col, int n);
    // Clears the number of the specified Cell.
    public void clearCellNumber(int row, int col);
    
    // Adds candidate to specified Cell.
    public void addCellCandidate(int row, int col, int n);
    // Add all visible candidates to specified cell.
    public void addAllCellCandidates(int row, int col);
    // Removes candidate from specified Cell.
    public void removeCellCandidate(int row, int col, int n);
    // Checks if specified Cell from the currentGrid has specified candidiate
    public boolean hasCellCandidate(int row, int col, int candidate);
    // Returns an ordered ArrayList of all the candidates from specified Cell.
    public ArrayList<Integer> getCandidates(int row, int col);
    // Clears all candidates from specified Cell.
    public void clearCellCandidates(int row, int col);
    // Adds all candidates. (Clears candidate for a cell, then adds the candidates)
    public void addAllCandidates();
    // Clears all candidates. (from the entire grid)
    public void clearAllCandidates();
    
    // Checks if the number matches the answer for the cell
    public boolean isCellCorrect(int row, int col);
    // Checks if the entire grid is correct.
    public boolean isGridCorrect();
    // Checks if the specified row is correct.
    public boolean isRowCorrect(int row);
    // Checks if the specified column is correct.
    public boolean isColumnCorrect(int col);
    // Checks if the specified box is correct.
    public boolean isBoxCorrect(int box);
    
    // Reveals a random blank cell (changes to the correct answer)
    public void revealRandom();
    // Reveals specified cell (changes to the correct answer, can be blank or not blank).
    public void revealCell(int row, int col);
    
    // Things that can be undo/redo for the moment:
    // giveCellNumber, removeCellNumber
    // setCellNumber, clearCellNumber
    // Undo
    public void undoMove();
    // Redo 
    public void redoMove();

    // Saves game to textfile to <specified location> with <specified name>
    public void saveGame(File save);
    // Load game from <specified location> (includes the file name)
    public void loadGame(File save);
    
    
    //*****************************************
    //********** Reveal phase methods *********
    //*****************************************
    // Checks if all numbers are filled in.
    public boolean isGridFilled();
    
    // Changes the current Grid to the solution.
    public void revealSolution();

    //*****************************************
    //************* General methods ***********
    //*****************************************
    public boolean isCellGiven(int row, int col);
    
    // Extra:
    // GET CONFLICTING (return positions of conflicting cells)
    // Change "pencil marks" to "pen" (changes all single candidates to "pen")
    // public void solveCandidates();
    // Pause feature (pauses time, hides puzzle) <-- Should be in GUI class
    // public void startTimer();
    // public void stopTimer();
    // public void pauseGame();
    // Fill candidates (fill all candidates for you)
    // public void fillCandidates();
    
}
