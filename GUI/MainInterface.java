
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;


/**
 * Controller and viewer for Sudoku application
 * 
 * @author Ben
 *
 */
public class MainInterface {
	private static final String ABOUT_MESSAGE = "2013s1 COMP2911 Project\n\nCreated by Benjamin Ki, Kenny Ho, Nathan Ho and Denaysh Selvakkumar.";
	
	public static void main(String[] Args) {
		JFrame mainFrame = new JFrame("Sudoku");
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setLayout(new GridBagLayout());
		
		GridBagConstraints boardConstraints = new GridBagConstraints();
		boardConstraints.anchor = GridBagConstraints.NORTH;
		boardConstraints.fill = GridBagConstraints.BOTH;
		boardConstraints.weightx = 1;
		boardConstraints.weighty = 1;
		BoardGUI b = new BoardGUI();
		JPanel grid = b.getBoard();
		mainFrame.getContentPane().add(grid, boardConstraints);
		
		GridBagConstraints inputConstraints = new GridBagConstraints();
		inputConstraints.gridy = 1;
		inputConstraints.fill = GridBagConstraints.BOTH;
		inputConstraints.anchor = GridBagConstraints.SOUTH;
		InputPanel inputPanel = new InputPanel();
		mainFrame.getContentPane().add(inputPanel, inputConstraints);
		
		JMenuBar menuBar = createMenuBar();

		mainFrame.setJMenuBar(menuBar);
		mainFrame.getContentPane().setPreferredSize(new Dimension(500, 500));
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private static JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu newGameMenu = new JMenu("New Game");
		newGameMenu.setMnemonic(KeyEvent.VK_N);
		newGameMenu.getAccessibleContext().setAccessibleDescription(
				"Create a new game");
		menuBar.add(newGameMenu);
		
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
		menuBar.add(optionsMenu);
		
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
		menuBar.add(aboutMenu);
		
		JMenuItem aboutItem = new JMenuItem("About Sudoku");
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, ABOUT_MESSAGE);
			}
		});
		aboutMenu.add(aboutItem);
		return menuBar;
	}
	
}
