public class HardGenerator extends SudokuGenerator{
	private static final int HARD_DIFFICULTY = 30;
	private static final int NUM_CELLS = 81;
	public HardGenerator(Grid currentGrid, Grid answerGrid) {
		super(currentGrid, answerGrid);
	}
	
	@Override
	protected void removeCells() {
		int remove = NUM_CELLS-HARD_DIFFICULTY;
        for(int i=0; i<remove; i++) {
            this.removeRandomCellInBox(i%9);
        }
	}
}
