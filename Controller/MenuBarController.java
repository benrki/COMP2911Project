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
		}
		
	}
	class customGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.generatePuzzle();// Change for difficulty later
		}
		
	}
	class optionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Option");
		}
		
	}
	
	
}
