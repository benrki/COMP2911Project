import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InputPanelController {
	private InputPanel inputPanel;
	private SudokuModel model;
	
	public InputPanelController(SudokuModel model, InputPanel inputPanel){
		this.model = model;
		this.inputPanel = inputPanel;
		this.inputPanel.addActionListener(new ClearPress(inputPanel.getBoard()), inputPanel.getClearButton());
		for(KeyButton k : inputPanel.getKeyButtons()){
			this.inputPanel.addActionListener(new KeyClick(inputPanel.getBoard(), k.getLabel()), k);
		}
	}
	
	class ClearPress implements ActionListener {
		private BoardPanel board;
		
		public ClearPress(BoardPanel board){
			this.board = board;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Position p = board.getSelectedCell();
			if (p != null) {
				model.clearCellNumber(p.getX(), p.getY());
				int curr = model.getCellNumber(p.getX(), p.getY());
				if (curr == 0) {
					board.getButton(p).setText(null);
				} else {
					board.getButton(p).setText(Integer.toString(curr));
				}
			}
		}
	}
	
	class KeyClick implements ActionListener {
		
		private BoardPanel board;
		private String label;
		private Font font;
		
		public KeyClick(BoardPanel board, String label){
			this.board = board;
			this.label = label;
			this.font = new Font("sansserif",Font.BOLD,36);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Position p = board.getSelectedCell();
			if (p != null) {
				model.setCellNumber(p.getX(), p.getY(), Integer.parseInt(label));
				board.getSelectedButton().setText(label);
				board.getSelectedButton().setFont(font);
			}
		}
	}
}