
public class EasyGenerator extends SudokuGenerator{
	private static final int EASY_DIFFICULTY = 60;
	private static final int NUM_CELLS = 81;
	public EasyGenerator(Grid currentGrid, Grid answerGrid) {
		super(currentGrid, answerGrid);
	}
	
	@Override
	protected void removeCells() {
        int remove = NUM_CELLS-EASY_DIFFICULTY;
        for(int i=0; i<remove; i++) {
            this.removeRandomCellInBox(i%9);
        }
    }
}
