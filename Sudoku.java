
public class Sudoku {

	public static void main(String[] args) {
		SudokuModel model = new SudokuModel();
		Viewer mainInterface = new Viewer();
		Controller controller = new Controller(model, mainInterface);
		mainInterface.setVisible(true);
	}
}
