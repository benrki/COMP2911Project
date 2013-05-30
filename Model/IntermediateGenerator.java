public class IntermediateGenerator extends SudokuGenerator{
    // The number of "given" cells.
	private static final int INTERMEDIATE_DIFFICULTY = 45;

	public IntermediateGenerator(Grid currentGrid, Grid answerGrid) {
		super(currentGrid, answerGrid);
	}
	
	@Override
	protected void removeCells() {
        int remove = Grid.NUM_CELLS - INTERMEDIATE_DIFFICULTY;
        for(int i=0; i<remove; i++) {
            this.removeRandomCellInBox(i%9);
        }
	}
}
