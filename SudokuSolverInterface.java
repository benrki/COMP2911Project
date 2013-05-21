
public interface SudokuSolverInterface {
    
    // Complete the answer grid
    public boolean findAnswers();
    // Sets all the numbers in the currentGrid to the numbers in the answerGrid.
    public void solveGrid();
    // Gets the number of the specified Cell specified from the currentGrid
    public int getCurrentCellNumber(int row, int col);
    // Sets the number of the specified Cell from the currentGrid
    public void setCurrentCellNumber(int row, int col, int n);
    /* Sets the number in the specified cell in the currentGrid as the specified number. 
     * (Different to setCurrentCellNumber as it prevents the number from being changed in the future).
     * Should be used when initialising the board.
     */
    public void giveCellNumber(int row, int col, int n);
    public boolean isCurrentCellCorrect(int row, int col);
    
    // Checks if specified Cell from the currentGrid has specified candidiate
    public boolean currentCellHasCandidate(int row, int col, int candidate);
    public void addCurrentCellCandidates(int row, int col, int n);
    // Checks if currentCell is CORRECT (different to valid)
    public boolean isCurrentCellValid(int row, int col);
    
    public boolean isGridCorrect();
    public boolean isRowCorrect(int row);
    public boolean isColumnCorrect(int col);
    public boolean isBoxCorrect(int box);
    
    // Undo
    // Redo
    // Clear input
    // Clear possibles
    // Start time
    // Finish time
    
    // Extra:
    // Change "pencil marks" to "pen" (changes all single candidates to "pen")
    // Pause feature (pauses time, hides puzzle) <-- Should be in GUI class
    // Fill candidates (fill all candidates for you)
    
}
