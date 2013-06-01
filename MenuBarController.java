import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class MenuBarController {
	private MenuBar menuBar;
	private SudokuModel model;
	private BoardPanelController boardController;
	private JFrame frame;
	
	public MenuBarController(SudokuModel model, MenuBar menuBar, BoardPanelController boardController, JFrame frame) {
		this.model = model;
		this.menuBar = menuBar;
		this.boardController = boardController;
		this.frame = frame;
		this.menuBar.addActionListener(new easyGameListener(), menuBar.getEasy());
		this.menuBar.addActionListener(new mediumGameListener(), menuBar.getMedium());
		this.menuBar.addActionListener(new hardGameListener(), menuBar.getHard());
		this.menuBar.addActionListener(new customGameListener(), menuBar.getCustom());
		this.menuBar.addActionListener(new loadGameListener(), menuBar.getLoadGame());
		this.menuBar.addActionListener(new saveGameListener(), menuBar.getSaveGame());
		this.menuBar.addActionListener(new submitListener(), menuBar.getSubmit());
		this.menuBar.addActionListener(new checkListener(), menuBar.getCheck());
		this.menuBar.addActionListener(new exitListener(), menuBar.getExit());
		this.menuBar.addActionListener(new undoListener(), menuBar.getUndo());
		this.menuBar.addActionListener(new redoListener(), menuBar.getRedo());
		this.menuBar.addActionListener(new solveCellListener(), menuBar.getSolveCell());
		this.menuBar.addActionListener(new solveRandomListener(), menuBar.getSolveRandom());
		this.menuBar.addActionListener(new solveBoardListener(), menuBar.getSolve());
		this.menuBar.addActionListener(new clearCandidatesListener(), menuBar.getClearCandidates());
		this.menuBar.addActionListener(new warnVisibleListener(), menuBar.getWarnVisibleMistakes());
		this.menuBar.addActionListener(new showMistakesListener(), menuBar.getShowVisibleMistakes());
		this.menuBar.addActionListener(new showNumListener(), menuBar.getShowNumWrong());
	}
	
	/**
	 * @author Ben
	 *
	 */
	class easyGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.generateEasyPuzzle();
			boardController.updateBoard();
		}
		
	}
	
	/**
	 * @author Ben
	 *
	 */
	class mediumGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.generateIntermediatePuzzle();
			boardController.updateBoard();
		}
		
	}
	
	/**
	 * @author Ben
	 *
	 */
	class hardGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.generateHardPuzzle();
			boardController.updateBoard();
		}
		
	}
	
	class customGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class loadGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                model.loadGame(file);
                boardController.updateBoard();
            } 
		}
		
	}
	
	class saveGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showDialog(null, "Save");
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                model.saveGame(file);
                System.out.println(file.getParent());
                boardController.updateBoard();
            } 
		}
	}
	
	class submitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	class checkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}

	}

	class exitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int result = JOptionPane.showConfirmDialog(
					frame,
					"Are you sure you want to exit the application?",
					"Exit Application",
					JOptionPane.YES_NO_OPTION);

			if (result == JOptionPane.YES_OPTION) {
				frame.setVisible(false);
				frame.dispose();
			}
		}
	}
	
	class undoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.undoMove();
			boardController.updateBoard();
		}
		
	}
	
	class redoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.redoMove();
			boardController.updateBoard();
		}
		
	}
	
	class solveCellListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	class solveRandomListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	class solveBoardListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boardController.solve();
		}
		
	}
	
	class clearCandidatesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	class warnVisibleListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	class showMistakesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	class showNumListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
}
