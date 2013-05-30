public class HardGenerator extends SudokuGenerator{
    // The number of "given" cells.
	private static final int HARD_DIFFICULTY = 30;

	public HardGenerator(Grid currentGrid, Grid answerGrid) {
		super(currentGrid, answerGrid);
	}
	
	@Override
	protected void removeCells() {
		int remove = Grid.NUM_CELLS - HARD_DIFFICULTY;
        for(int i=0; i<remove; i++) {
            this.removeRandomCellInBox(i%9);
        }
	}
}
