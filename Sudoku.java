
public class Sudoku {

	public static void main(String[] args) {
		Model model = new Model();
		Viewer mainInterface = new Viewer();
		Controller controller = new Controller(model, mainInterface);
		mainInterface.setVisible(true);
	}
}
