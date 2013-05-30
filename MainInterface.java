
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;


/**
 * Controller and viewer for Sudoku application
 * 
 * @author Ben
 *
 */
public class MainInterface extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SudokuModel model;
	private MainController mainController;
	private BoardPanel board;
	private BoardPanelController boardController;
	private InputPanel inputPanel;
	private InputPanelController inputController;
	private MenuBar menuBar;
	private MenuBarController menuController;

	public MainInterface() {
		super("Sudoku");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setLayout(new GridBagLayout());
		mainController = new MainController();
		model = new SudokuModel();
		
		GridBagConstraints inputConstraints = new GridBagConstraints();
		inputConstraints.gridy = 1;
		inputConstraints.fill = GridBagConstraints.BOTH;
		inputConstraints.anchor = GridBagConstraints.SOUTH;
		inputPanel = new InputPanel(board);
		inputController = new InputPanelController(model, inputPanel);
		super.getContentPane().add(inputPanel, inputConstraints);
		
		GridBagConstraints boardConstraints = new GridBagConstraints();
		boardConstraints.anchor = GridBagConstraints.NORTH;
		boardConstraints.fill = GridBagConstraints.BOTH;
		boardConstraints.weightx = 1;
		boardConstraints.weighty = 1;
		board = new BoardPanel();
		boardController = new BoardPanelController(board, model, inputPanel);
		super.getContentPane().add(board, boardConstraints);
		
		menuBar = new MenuBar();
		menuController = new MenuBarController(model, menuBar, boardController, this);

		super.setJMenuBar(menuBar);
		super.getContentPane().setPreferredSize(new Dimension(500, 500));
		super.pack();
	}

	
}
