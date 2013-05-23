import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InputPanelController {

}

class ClearPress implements ActionListener {
	private BoardGUI board;
	
	public ClearPress(BoardGUI board){
		this.board = board;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(board.getSelectedCell() != null){
			board.getSelectedButton().setText(null);
		}
	}
}