import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;


public class MenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ABOUT_MESSAGE = "2013s1 COMP2911 Project\n\nCreated by Benjamin Ki, Kenny Ho, Nathan Ho and Denaysh Selvakkumar.";
	
	JMenu fileMenu;
	JMenu newGameMenu;
	JMenuItem easy;
	JMenuItem medium;
	JMenuItem hard;
	JMenuItem custom;
	JMenuItem loadGame;
	JMenuItem saveGame;
	JMenuItem saveGameAs;
	JMenuItem submit;
	JMenuItem check;
	JMenuItem exit;
	JMenu editMenu;
	JMenuItem undo;
	JMenuItem redo;
	JMenuItem solveCell;
	JMenuItem solveRandom;
	JMenuItem clearCandidates;
	JMenuItem solve;
	JMenu prefMenu;
	JMenuItem warnVisibleMistakes;
	JMenuItem showVisibleMistakes;
	JMenuItem showNumWrong;
	JMenu aboutMenu;
	JMenuItem aboutItem;
	
	
	public MenuBar() {
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.getAccessibleContext().setAccessibleDescription(
				"File");
		this.add(fileMenu);
		
		newGameMenu = new JMenu("New Game");
		newGameMenu.setMnemonic(KeyEvent.VK_N);
		newGameMenu.getAccessibleContext().setAccessibleDescription(
				"Create a new game");
		fileMenu.add(newGameMenu);
		
		easy = new JMenuItem("Easy");
		easy.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		newGameMenu.add(easy);
		
		medium = new JMenuItem("Medium");
		medium.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_2, ActionEvent.ALT_MASK));
		newGameMenu.add(medium);
		
		hard = new JMenuItem("Hard");
		hard.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_3, ActionEvent.ALT_MASK));
		newGameMenu.add(hard);
		
		custom = new JMenuItem("Custom");
		custom.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_3, ActionEvent.ALT_MASK));
		newGameMenu.add(custom);
		
		loadGame = new JMenuItem("Load Game");
		loadGame.setMnemonic(KeyEvent.VK_L);
		loadGame.getAccessibleContext().setAccessibleDescription(
				"Load a game");
		fileMenu.add(loadGame);
		
		saveGame = new JMenuItem("Save Game");
		saveGame.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, ActionEvent.ALT_MASK));
		fileMenu.add(saveGame);
		
		submit = new JMenuItem("Submit");
		submit.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_A, ActionEvent.ALT_MASK));
		fileMenu.add(submit);
		
		check = new JMenuItem("Check");
		check.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_A, ActionEvent.ALT_MASK));
		fileMenu.add(check);
		
		exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_E, ActionEvent.ALT_MASK));
		fileMenu.add(exit);
		
		editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);
		editMenu.getAccessibleContext().setAccessibleDescription(
				"Edit");
		this.add(editMenu);
		
		undo = new JMenuItem("Undo");
		undo.setMnemonic(KeyEvent.VK_U);
		undo.getAccessibleContext().setAccessibleDescription(
				"Undo a move");
		editMenu.add(undo);
		
		redo = new JMenuItem("Redo");
		redo.setMnemonic(KeyEvent.VK_R);
		redo.getAccessibleContext().setAccessibleDescription(
				"Redo a move");
		editMenu.add(redo);
		
		solveCell = new JMenuItem("Solve Cell");
		solveCell.setMnemonic(KeyEvent.VK_C);
		solveCell.getAccessibleContext().setAccessibleDescription(
				"Solve a specific cell");
		editMenu.add(solveCell);
		
		solveRandom = new JMenuItem("Solve Random");
		solveRandom.setMnemonic(KeyEvent.VK_R);
		solveRandom.getAccessibleContext().setAccessibleDescription(
				"Solve a random cell");
		editMenu.add(solveRandom);
		
		solve = new JMenuItem("Solve Board");
		solve.setMnemonic(KeyEvent.VK_R);
		solve.getAccessibleContext().setAccessibleDescription(
				"Solve entire board");
		editMenu.add(solve);
		
		clearCandidates = new JMenuItem("Clear Candidates");
		clearCandidates.setMnemonic(KeyEvent.VK_R);
		clearCandidates.getAccessibleContext().setAccessibleDescription(
				"Clear candidates");
		editMenu.add(clearCandidates);
		
		prefMenu = new JMenu("Preferences");
		prefMenu.setMnemonic(KeyEvent.VK_P);
		prefMenu.getAccessibleContext().setAccessibleDescription(
				"Application settings");
		this.add(prefMenu);

		warnVisibleMistakes = new JMenuItem("Warn Visible Mistakes");
		warnVisibleMistakes.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_W, ActionEvent.ALT_MASK));
		prefMenu.add(warnVisibleMistakes);
		
		showVisibleMistakes = new JMenuItem("Show Visible Mistakes");
		showVisibleMistakes.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, ActionEvent.ALT_MASK));
		prefMenu.add(showVisibleMistakes);
		
		showNumWrong = new JMenuItem("Show Wrong Numbers");
		showNumWrong.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_N, ActionEvent.ALT_MASK));
		prefMenu.add(showNumWrong);
		
		aboutMenu = new JMenu("About");
		aboutMenu.setMnemonic(KeyEvent.VK_A);
		aboutMenu.getAccessibleContext().setAccessibleDescription(
				"About this program");
		this.add(aboutMenu);
		
		aboutItem = new JMenuItem("About Sudoku");
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, ABOUT_MESSAGE);
			}
		});
		aboutMenu.add(aboutItem);
	}
	
	public void addActionListener(ActionListener l, JMenuItem item) {
		item.addActionListener(l);
	}
	
	public JMenuItem getEasy() {
		return easy;
	}

	public JMenuItem getMedium() {
		return medium;
	}

	public JMenuItem getHard() {
		return hard;
	}

	public JMenuItem getCustom() {
		return custom;
	}

	public JMenuItem getLoadGame() {
		return loadGame;
	}

	public JMenuItem getSaveGame() {
		return saveGame;
	}

	public JMenuItem getSubmit() {
		return submit;
	}

	public JMenuItem getCheck() {
		return check;
	}

	public JMenuItem getExit() {
		return exit;
	}

	public JMenuItem getUndo() {
		return undo;
	}

	public JMenuItem getRedo() {
		return redo;
	}

	public JMenuItem getSolveCell() {
		return solveCell;
	}

	public JMenuItem getSolveRandom() {
		return solveRandom;
	}

	public JMenuItem getClearCandidates() {
		return clearCandidates;
	}

	public JMenuItem getSolve() {
		return solve;
	}

	public JMenuItem getWarnVisibleMistakes() {
		return warnVisibleMistakes;
	}

	public JMenuItem getShowVisibleMistakes() {
		return showVisibleMistakes;
	}

	public JMenuItem getShowNumWrong() {
		return showNumWrong;
	}

	public JMenuItem getAboutItem() {
		return aboutItem;
	}

}
