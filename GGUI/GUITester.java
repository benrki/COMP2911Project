import java.awt.BorderLayout;

import javax.swing.JFrame;


public class GUITester {
	public static void main(String[] args){
		BoardGUI board = new BoardGUI();
		BoardGUIController boardController = new BoardGUIController(board);
		InputPanel inputPanel = new InputPanel(board);
		JFrame frame = new JFrame("WOO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(board.getBoard(), BorderLayout.CENTER);
		frame.add(inputPanel, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(600, 600);
		frame.isResizable();
	}
}
