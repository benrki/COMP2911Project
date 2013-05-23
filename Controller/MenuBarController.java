import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class MenuBarController {
	private MenuBar menuBar;
	private SudokuModel model;
	private BoardPanelController boardController;
	
	public MenuBarController(SudokuModel model, MenuBar menuBar, BoardPanelController boardController) {
		this.model = model;
		this.menuBar = menuBar;
		this.boardController = boardController;
		this.menuBar.addEasyGameListener(new easyGameListener());
		this.menuBar.addMediumGameListener(new mediumGameListener());
		this.menuBar.addHardGameListener(new hardGameListener());
		this.menuBar.addCustomGameListener(new customGameListener());
		this.menuBar.addSolveListener(new solveListener());
		this.menuBar.addOptionListener(new optionListener());
	}
	
	/**
	 * @author Ben
	 *
	 */
	class easyGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.generatePuzzle();	// Change for difficulty later
			boardController.setBoard(model);
		}
		
	}
	
	/**
	 * @author Ben
	 *
	 */
	class mediumGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.generatePuzzle();// Change for difficulty later
			boardController.setBoard(model);
		}
		
	}
	
	/**
	 * @author Ben
	 *
	 */
	class hardGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.generatePuzzle();// Change for difficulty later
			boardController.setBoard(model);
		}
		
	}
	
	class customGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.generatePuzzle();// Change for difficulty later
			boardController.setBoard(model);
		}
		
	}
	
	class solveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Solve sudoku
			boardController.solve(model);
		}
		
	}
	
	class optionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Option");
		}
		
	}
	
	
}
