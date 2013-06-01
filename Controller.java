
public class Controller {
	private ControllerBoardPanel boardController;
	private ControllerInputPanel inputController;
	private ControllerMenuBar menuController;
	
	public Controller(SudokuModel model, Viewer mainInterface) {

		this.inputController = new ControllerInputPanel(model, mainInterface.getInputPanel());
		this.boardController = new ControllerBoardPanel(mainInterface.getBoard(), model, inputController);
		this.menuController = new ControllerMenuBar(model, mainInterface.getMenuBarViewer(), boardController);
	}

}
