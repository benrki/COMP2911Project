import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class KeyClick implements ActionListener {
	
	private BoardPanel board;
	private String label;
	
	public KeyClick(BoardPanel board, String label){
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
