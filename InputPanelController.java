import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class InputPanelController {
	private InputPanel inputPanel;
	private SudokuModel model;
	
	public InputPanelController(SudokuModel model, InputPanel inputPanel) {
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
				if(!inputPanel.getCandidateButton().isSelected()){
					model.clearCellNumber(p.getRow(), p.getCol());
					int curr = model.getCellNumber(p.getRow(), p.getCol());
					if (curr == 0) {
						board.getButton(p).setNumberLabel(null);
					} else {
						board.getButton(p).setNumberLabel(Integer.toString(curr));
					}
				}else{
					model.clearCellCandidates(p.getRow(), p.getCol());
					board.getButton(p).clearCandidateLabel();
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
			this.font = new Font("sansserif",Font.BOLD,18);
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			setSelectedCell();
		}

		private void setSelectedCell() {
			Position p = board.getSelectedCell();
			if (p != null && !model.isCellGiven(p.getRow(), p.getCol())) {
				if(!inputPanel.getCandidateButton().isSelected()){
					model.setCellNumber(p.getRow(), p.getCol(), Integer.parseInt(label));
					board.getSelectedButton().setNumberLabel(label);
				}else{
					if(model.hasCellCandidate(p.getRow(), p.getCol(), Integer.parseInt(label))){
					//	System.out.println(label);
						model.removeCellCandidate(p.getRow(), p.getCol(), Integer.parseInt(label));
						board.getSelectedButton().setCandidateLabel(model.getCandidates(p.getRow(), p.getCol()));
					}else{
					//	System.out.println(label);
						model.addCellCandidate(p.getRow(), p.getCol(), Integer.parseInt(label));
						board.getSelectedButton().setCandidateLabel(model.getCandidates(p.getRow(), p.getCol()));
					}
				}
			}
		}

	//	private void checkFinished() {
	//		if (model.isSudokuFinished()) {
	//			JOptionPane.showMessageDialog(null, "Trophy 4 u!");
	//		}
	//	}
	}
	
}