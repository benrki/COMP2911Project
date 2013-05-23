import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * @author Ben
 *
 */
public class InputPanel extends JPanel  {
	
	/**
	 * Default serialization
	 */
	private static final long serialVersionUID = 1L;

	public InputPanel() {
		super();
		String keyLabels = "789456123";
		this.setLayout(new GridLayout(4, 3));
		for (int i = 0; i < keyLabels.length(); i++) {
			final String label = keyLabels.substring(i, i + 1);
			JButton keyButton = new JButton(label);
			this.add(keyButton);
			keyButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					// Send label to be the next action
					// Integer.parseInt(label)
				}
			});
		}
		
		JButton hint = new JButton("HINT");
		this.add(hint);
		hint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Clear selected cell if number entered
			}
		});
		
		JButton clear = new JButton("CLEAR");
		this.add(clear);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Clear selected cell if number entered
			}
		});

		JToggleButton mark = new JToggleButton("MARK");
		this.add(mark);
		mark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Toggle candidate marking mode
			}
		});
	}	
}
