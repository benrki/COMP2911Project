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
	JMenu newGameMenu;
	JMenuItem easy;
	JMenuItem medium;
	JMenuItem hard;
	JMenuItem custom;
	JMenu optionsMenu;
	JMenuItem option;
	JMenu aboutMenu;
	JMenuItem aboutItem;
	
	
	public MenuBar() {
		super();
		newGameMenu = new JMenu("New Game");
		newGameMenu.setMnemonic(KeyEvent.VK_N);
		newGameMenu.getAccessibleContext().setAccessibleDescription(
				"Create a new game");
		this.add(newGameMenu);
		
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
		
		optionsMenu = new JMenu("Settings");
		optionsMenu.setMnemonic(KeyEvent.VK_S);
		optionsMenu.getAccessibleContext().setAccessibleDescription(
				"Application settings");
		this.add(optionsMenu);
		
		option = new JMenuItem("Filler");
		option.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		optionsMenu.add(option);
		
		aboutMenu = new JMenu("About");
		aboutMenu.setMnemonic(KeyEvent.VK_O);
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
	
	void addEasyGameListener(ActionListener l) {
		easy.addActionListener(l);
	}

	void addMediumGameListener(ActionListener l) {
		medium.addActionListener(l);
	}

	void addHardGameListener(ActionListener l) {
		hard.addActionListener(l);
	}

	void addCustomGameListener(ActionListener l) {
		custom.addActionListener(l);
	}

	void addOptionListener(ActionListener l) {
		option.addActionListener(l);
	}

}
