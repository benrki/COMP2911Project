import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;



public class ControllerBoardPanel {
	private ViewerBoardPanel board;
	private ViewerInputPanel inputPanel;
	private Model model;
	
	public ControllerBoardPanel(ViewerBoardPanel board, Model model, ViewerInputPanel inputPanel){
		this.board = board;
		this.inputPanel = inputPanel;
		this.model = model;
		
		for(ArrayList<ViewerCellButton> a : board.getButtons()){
			for(ViewerCellButton b : a){
				board.addActionListener(new SelectCell(b), b);
				board.addKeyListener(new KeyPress(b), b);
			}
		}
	}

	class SelectCell implements ActionListener {
		private ViewerCellButton selectedButton;
		
		SelectCell(ViewerCellButton cell){
			this.selectedButton = cell;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// select tile make it yellow
			try{
				if(board.getSelectedButton() == null){
					board.setSelectedCell(selectedButton.getPosition());
					selectedButton.setBackground(Color.YELLOW);
				//  debugging messages
				//	System.out.println("You selected " + board.getSelectedCell().getRow() + " " + board.getSelectedCell().getCol());
		
				}else{
					board.getSelectedButton().setBackground(Color.WHITE);
					board.setSelectedCell(selectedButton.getPosition());
					selectedButton.setBackground(Color.YELLOW);
				//  debugging messages
				//	System.out.println("You selected " + board.getSelectedCell().getRow() + " " + board.getSelectedCell().getCol());
				}
			} catch (NullPointerException o){
				System.out.println("sumting wong");
			}
		}
	}
	
	public void updateBoard() {
		for (ArrayList<ViewerCellButton> cbList: board.getButtons()) {
			for (ViewerCellButton cb : cbList) {
				cb.clearNumberLabel();
				cb.clearCandidateLabel();
				int x = cb.getPosition().getRow();
				int y = cb.getPosition().getCol();
				Position p = new Position(x, y);
				int curr = (model.getCellNumber(x, y));
				if (curr != 0) {
					board.getButton(p).setNumberLabel(Integer.toString(curr));
				}
				if(model.isCellGiven(p.getRow(), p.getCol())){
					board.getButton(p).setNumberColor(Color.BLACK);
				}else{
					board.getButton(p).setNumberColor(Color.BLUE);
					board.getButton(p).setCandidateColor(Color.BLUE);
				}
			}
		}
	}
	
	public void solve() {
		model.revealSolution();
		this.updateBoard();
	}
	
	class KeyPress implements KeyListener {
		
		private String label;

		public KeyPress(JButton b){
			this.label = "123456789";
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			String pressedKey = KeyEvent.getKeyText(arg0.getKeyChar());
			Position p = board.getSelectedCell();
			if(p != null){
				if(label.contains(pressedKey)){
					if (!model.isCellGiven(p.getRow(), p.getCol())) {
						if(!inputPanel.getCandidateButton().isSelected()){
							model.setCellNumber(p.getRow(), p.getCol(), Integer.parseInt(pressedKey));
							board.getSelectedButton().setNumberLabel(pressedKey);
						}else{
							if(model.hasCellCandidate(p.getRow(), p.getCol(), Integer.parseInt(pressedKey))){
							//	System.out.println(label);
								model.removeCellCandidate(p.getRow(), p.getCol(), Integer.parseInt(pressedKey));
								board.getSelectedButton().setCandidateLabel(model.getCandidates(p.getRow(), p.getCol()));
							}else{
							//	System.out.println(label);
								model.addCellCandidate(p.getRow(), p.getCol(), Integer.parseInt(pressedKey));
								board.getSelectedButton().setCandidateLabel(model.getCandidates(p.getRow(), p.getCol()));
							}
						}
					}
				}else{
					if(arg0.getKeyCode() == KeyEvent.VK_UP && p.getRow() != 0){
						board.getSelectedButton().setBackground(Color.WHITE);
						board.setSelectedCell(new Position(p.getRow()-1, p.getCol()));
						board.getSelectedButton().setBackground(Color.YELLOW);
						System.out.println("up");
					}else if(arg0.getKeyCode() == KeyEvent.VK_DOWN && p.getRow() != 8){
						board.getSelectedButton().setBackground(Color.WHITE);
						board.setSelectedCell(new Position(p.getRow()+1, p.getCol()));
						board.getSelectedButton().setBackground(Color.YELLOW);
						System.out.println("down");
					}else if(arg0.getKeyCode() == KeyEvent.VK_LEFT && p.getCol() != 0){
						board.getSelectedButton().setBackground(Color.WHITE);
						board.setSelectedCell(new Position(p.getRow(), p.getCol()-1));
						board.getSelectedButton().setBackground(Color.YELLOW);
						System.out.println("left");
					}else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT && p.getCol() != 8){
						board.getSelectedButton().setBackground(Color.WHITE);
						board.setSelectedCell(new Position(p.getRow(), p.getCol()+1));
						board.getSelectedButton().setBackground(Color.YELLOW);
						System.out.println("right");
					}
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {	
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}
}

