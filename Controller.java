
public class Controller {
	private ControllerBoardPanel boardController;
	private ControllerInputPanel inputController;
	private ControllerMenuBar menuController;
	
	public Controller(SudokuModel model, Viewer mainInterface) {
<<<<<<< HEAD

=======
		this.boardController = new ControllerBoardPanel(mainInterface.getBoard(), model, mainInterface.getInputPanel());
>>>>>>> ba8a3e98ba6fb98b68d338765d7e89dc8861aaeb
		this.inputController = new ControllerInputPanel(model, mainInterface.getInputPanel());
		this.boardController = new ControllerBoardPanel(mainInterface.getBoard(), model, inputController);
		this.menuController = new ControllerMenuBar(model, mainInterface.getMenuBarViewer(), boardController);
	}

}
