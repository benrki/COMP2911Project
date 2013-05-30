
public class IntermediateGenerator extends SudokuGenerator{
	private static final int INTERMEDIATE_DIFFICULTY = 45;
	private static final int NUM_CELLS = 81;
	public IntermediateGenerator(Grid currentGrid, Grid answerGrid) {
		super(currentGrid, answerGrid);
	}
	
	@Override
	protected void removeCells() {
        int remove = NUM_CELLS-INTERMEDIATE_DIFFICULTY;
        for(int i=0; i<remove; i++) {
            this.removeRandomCellInBox(i%9);
        }
	}
}
