import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class KeyClick implements ActionListener {
	
	private BoardGUI board;
	private String label;
	
	public KeyClick(BoardGUI board, String label){
		this.board = board;
		this.label = label;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(board.getSelectedCell() != null){
			board.getSelectedButton().setText(label);
		}
	}

}
