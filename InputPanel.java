import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
public class InputPanel implements KeyListener {

	public InputPanel() {
		JFrame frame = new JFrame("Input Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String keyLabels = "7894561230";
		final JPanel keyPanel = new JPanel();
		keyPanel.setLayout(new GridLayout(4, 3));
		for (int i = 0; i < keyLabels.length(); i++) {
			final String label = keyLabels.substring(i, i + 1);
			JButton keyButton = new JButton(label);
			keyPanel.add(keyButton);
			keyButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					// Send label to be the next action
					// Integer.parseInt(label)
				}
			});
		}
		
		JButton clear = new JButton("CLEAR");
		keyPanel.add(clear);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Clear selected cell if number entered
			}
		});

		JToggleButton mark = new JToggleButton("MARK");
		keyPanel.add(mark);
		mark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Toggle candidate marking mode
			}
		});

		// Requests keyPanel get the focus whenever frame is activated.
		frame.addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e) {
				// Remove later
				System.out.println("keyPanel in focus");
				// Requires keyPanel be final? Ask Sam about this
				keyPanel.requestFocusInWindow();
			}
		});

		keyPanel.addKeyListener(this);

		frame.add(keyPanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// Do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Sends keyboard input to be next action for a currently selected tile
		
		// Remove later
		System.out.println(e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Do nothing
	}
}
