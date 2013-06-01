import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class ControllerInputPanel {
	private ViewerInputPanel inputPanel;

	private ViewerBoardPanel board;
	private SudokuModel model;
	
	public ControllerInputPanel(SudokuModel model, ViewerInputPanel inputPanel) {
		this.model = model;
		this.inputPanel = inputPanel;
		this.board = inputPanel.getBoard();
		this.inputPanel.addActionListener(new ClearPress(), inputPanel.getClearButton());
		for(ViewerKeyButton k : inputPanel.getKeyButtons()){
			this.inputPanel.addActionListener(new KeyClick(inputPanel.getBoard(), k.getLabel()), k);
		}
	}
	
	class ClearPress implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			clearSelectedCell();
			showInvalid();
			showIncorrect();
		}
		
		private void clearSelectedCell(){
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

		private String label;
		
		public KeyClick(ViewerBoardPanel board, String label){
			this.label = label;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Position p = board.getSelectedCell();
			setSelectedCell(label, p);
			showInvalid();
			showIncorrect();
		}

	//	private void checkFinished() {
	//		if (model.isSudokuFinished()) {
	//			JOptionPane.showMessageDialog(null, "Trophy 4 u!");
	//		}
	//	}
	}
	
	public void setSelectedCell(String label, Position p) {
		if (!model.isCellGiven(p.getRow(), p.getCol())) {
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
	
	public void showInvalid(){
		for(int i=0; i < 9; i++){
			for(int j=0; j < 9; j++){
				board.getButtons().get(i).get(j).setValid(model.isCellValid(i, j));
			}
		}
	}
	/*	if(!model.isBoxValid(curBox)){
			for(int i=(curBox/3)*3; i<(curBox/3)*3+3; i++) {
				for(int j=(curBox%3)*3; j<(curBox%3)*3+3; j++) {
					board.getButtons().get(i).get(j).setValid(false);
				}
			}
		}else{
			for(int i=(curBox/3)*3; i<(curBox/3)*3+3; i++) {
				for(int j=(curBox%3)*3; j<(curBox%3)*3+3; j++) {
					if(model.isCellValid(board.getButtons().get(i).get(j).getPosition().getRow(), board.getButtons().get(i).get(j).getPosition().getCol())){
						board.getButtons().get(i).get(j).setValid(true);
					}
				}
			}
			if(!model.isRowValid(curRow)){
				for(int i = 0; i < 9; i ++){
					board.getButtons().get(curRow).get(i).setValid(false);
				}
			}else{
				for(int i = 0; i < 9; i ++){
					if(model.isCellValid(board.getButtons().get(curRow).get(i).getPosition().getRow(), board.getButtons().get(curRow).get(i).getPosition().getCol())){
						board.getButtons().get(curRow).get(i).setValid(true);
					}
				}
			}
			if(!model.isColumnValid(curCol)){
				for(int j = 0; j < 9; j++){
					board.getButtons().get(j).get(curCol).setValid(false);
				}
			}else{
				for(int j = 0; j < 9; j++){
					if(model.isCellValid(board.getButtons().get(j).get(curCol).getPosition().getRow(), board.getButtons().get(j).get(curCol).getPosition().getCol())){
						board.getButtons().get(j).get(curCol).setValid(true);
					}
				}
			}
		}
	}*/
	
	public void showIncorrect(){
		int curRow = board.getSelectedCell().getRow();
		int curCol = board.getSelectedCell().getCol();
		board.getSelectedButton().setCorrect(model.isCellCorrect(curRow, curCol));
	}
	
}