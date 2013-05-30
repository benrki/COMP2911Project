import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	
	private BoardPanel board;
	private JButton clearButton;
	private ArrayList<KeyButton> keyButtons;
	private JToggleButton candidateButton;

	public InputPanel(BoardPanel board) {
		super();
		this.board = board;
		this.keyButtons = new ArrayList<KeyButton>();
		String keyLabels = "789456123";
		this.setLayout(new GridLayout(4, 3));
		for (int i = 0; i < keyLabels.length(); i++) {
			final String label = keyLabels.substring(i, i + 1);
			KeyButton keyButton = new KeyButton(label);
			keyButton.setFocusable(false);
			this.add(keyButton);
			keyButtons.add(keyButton);
		}
		
		this.clearButton = new JButton("CLEAR");
		clearButton.setFocusable(false);
		this.add(clearButton);

		JToggleButton mark = new JToggleButton("MARK");
		mark.setFocusable(false);
		this.candidateButton = mark; // change name later
		this.add(mark);
		
		JButton hint = new JButton("HINT");
		this.add(hint);

	}	
	
	public BoardPanel getBoard(){
		return board;
	}
	
	public JButton getClearButton(){
		return clearButton;
	}
	
	public ArrayList<KeyButton> getKeyButtons(){
		return keyButtons;
	}
	
	public JToggleButton getCandidateButton(){
		return candidateButton;
	}
	
	public void addActionListener(ActionListener listener, JButton button){
		button.addActionListener(listener);
	}
}
