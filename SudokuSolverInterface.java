
public interface SudokuSolverInterface {
    /*
    // Complete the answer grid
    public boolean findAnswers();
    // Sets all the numbers in the currentGrid to the numbers in the answerGrid.
    public void solveGrid();
    // Gets the number of the specified Cell specified from the currentGrid
    public int getCurrentCellNumber(int row, int col);
    // Sets the number of the specified Cell from the currentGrid
    */
    public void setCurrentCellNumber(int row, int col, int n);
    /*
    // Checks if specified Cell from the currentGrid has specified candidiate
    public boolean currentCellHasCandidate(int row, int col, int candidate);
    public void addCurrentCellCandidates(int row, int col, int n);
    public boolean checkCurrentCell(int row, int col);
    
    // Undo
    // Redo
    // Clear input
    // Clear possibles
    
    // Extra:
    // Change "pencil marks" to "pen" (changes all single candidates to "pen")
    // Pause feature (pauses time, hides puzzle) <-- Should be in GUI class
    // Fill candidates (fill all candidates for you)
    */
}
