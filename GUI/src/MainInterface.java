
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


/**
 * Controller and viewer for Sudoku application
 * 
 * @author Ben
 *
 */
public class MainInterface {

	public static void main(String[] Args) {
		JFrame mainFrame = new JFrame("Sudoku");
		GridBagConstraints c = new GridBagConstraints();
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setLayout(new GridBagLayout());
		
		c.anchor = GridBagConstraints.NORTH;
		BoardGUI b = new BoardGUI();
		JPanel grid = b.getBoard();
		mainFrame.getContentPane().add(grid, c);
		
		c.gridy = 1;
		c.anchor = GridBagConstraints.SOUTH;
		InputPanel inputPanel = new InputPanel();
		mainFrame.getContentPane().add(inputPanel, c);
		
		mainFrame.setSize(1000, 1000); // wtf no werk
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	private JMenuBar makeMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("A Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menu);
		
		return menuBar;
	}
	
}
