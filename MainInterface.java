
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
	private BoardGUI board;
	private BoardGUIController boardController;
	private InputPanel inputPanel;
	private InputPanelController inputController;
	private MenuBar menuBar;
	private MenuBarController menuController;

	public MainInterface() {
		super("Sudoku");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setLayout(new GridBagLayout());
		
		GridBagConstraints boardConstraints = new GridBagConstraints();
		boardConstraints.anchor = GridBagConstraints.NORTH;
		boardConstraints.fill = GridBagConstraints.BOTH;
		boardConstraints.weightx = 1;
		boardConstraints.weighty = 1;
		board = new BoardGUI();
		boardController = new BoardGUIController(board);
		super.getContentPane().add(board, boardConstraints);
		
		GridBagConstraints inputConstraints = new GridBagConstraints();
		inputConstraints.gridy = 1;
		inputConstraints.fill = GridBagConstraints.BOTH;
		inputConstraints.anchor = GridBagConstraints.SOUTH;
		inputPanel = new InputPanel();
		//inputController = new InputPanelController(board);
		super.getContentPane().add(inputPanel, inputConstraints);
		
		menuBar = new MenuBar();
		menuController = new MenuBarController(menuBar);

		super.setJMenuBar(menuBar);
		super.getContentPane().setPreferredSize(new Dimension(500, 500));
		super.pack();
	}

	
}
