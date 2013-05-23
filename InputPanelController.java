import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InputPanelController {
	
	
	public InputPanelController(InputPanel inputPanel){
		inputPanel.addActionListener(new ClearPress(inputPanel.getBoard()), inputPanel.getClearButton());
		for(KeyButton k : inputPanel.getKeyButtons()){
			inputPanel.addActionListener(new KeyClick(inputPanel.getBoard(), k.getLabel()), k);
		}
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
	
	class KeyClick implements ActionListener {
		
		private BoardGUI board;
		private String label;
		private Font font;
		
		public KeyClick(BoardGUI board, String label){
			this.board = board;
			this.label = label;
			this.font = new Font("sansserif",Font.BOLD,36);
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(board.getSelectedCell() != null){
				board.getSelectedButton().setText(label);
				board.getSelectedButton().setFont(font);
			}
		}
	}
}