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
	
	public MenuBar() {
		super();
		JMenu newGameMenu = new JMenu("New Game");
		newGameMenu.setMnemonic(KeyEvent.VK_N);
		newGameMenu.getAccessibleContext().setAccessibleDescription(
				"Create a new game");
		this.add(newGameMenu);
		
		JMenuItem easy = new JMenuItem("Easy");
		easy.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, "Filler option item");
			}
		});
		newGameMenu.add(easy);
		
		JMenuItem medium = new JMenuItem("Medium");
		medium.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_2, ActionEvent.ALT_MASK));
		medium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, "Filler option item");
			}
		});
		newGameMenu.add(medium);
		
		JMenuItem hard = new JMenuItem("Hard");
		hard.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_3, ActionEvent.ALT_MASK));
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, "Filler option item");
			}
		});
		newGameMenu.add(hard);
		
		JMenu optionsMenu = new JMenu("Settings");
		optionsMenu.setMnemonic(KeyEvent.VK_S);
		optionsMenu.getAccessibleContext().setAccessibleDescription(
				"Application settings");
		this.add(optionsMenu);
		
		JMenuItem temp = new JMenuItem("Filler");
		temp.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		temp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, "Filler option item");
			}
		});
		optionsMenu.add(temp);
		
		JMenu aboutMenu = new JMenu("About");
		aboutMenu.setMnemonic(KeyEvent.VK_O);
		aboutMenu.getAccessibleContext().setAccessibleDescription(
				"About this program");
		this.add(aboutMenu);
		
		JMenuItem aboutItem = new JMenuItem("About Sudoku");
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, ABOUT_MESSAGE);
			}
		});
		aboutMenu.add(aboutItem);
	}

}