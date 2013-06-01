
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MenuBar;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 * Controller and viewer for Sudoku application
 * 
 * @author Ben
 *
 */
public class Viewer extends JFrame {
	private ViewerBoardPanel board;
	private ViewerInputPanel inputPanel;
	private ViewerMenuBar menuBar;

	public Viewer() {
		super("Sudoku");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setLayout(new GridBagLayout());
		menuBar = new ViewerMenuBar();
		board = new ViewerBoardPanel();
		inputPanel = new ViewerInputPanel(board);
		
		GridBagConstraints inputConstraints = new GridBagConstraints();
		inputConstraints.gridy = 1;
		inputConstraints.fill = GridBagConstraints.BOTH;
		inputConstraints.anchor = GridBagConstraints.SOUTH;
		super.getContentPane().add(inputPanel, inputConstraints);
		
		GridBagConstraints boardConstraints = new GridBagConstraints();
		boardConstraints.anchor = GridBagConstraints.NORTH;
		boardConstraints.fill = GridBagConstraints.BOTH;
		boardConstraints.weightx = 1;
		boardConstraints.weighty = 1;
		
		super.getContentPane().add(board, boardConstraints);
		super.setJMenuBar(menuBar);
		super.getContentPane().setPreferredSize(new Dimension(500, 500));
		super.pack();
	}

	public ViewerBoardPanel getBoard() {
		return board;
	}

	public ViewerInputPanel getInputPanel() {
		return inputPanel;
	}

	public ViewerMenuBar getMenuBarViewer() {
		return menuBar;
	}

	
}
