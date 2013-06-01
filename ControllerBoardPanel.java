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
	private ControllerInputPanel inputPanelController;
	private Model model;
	
	public ControllerBoardPanel(ViewerBoardPanel board, Model model, ViewerInputPanel inputPanel, ControllerInputPanel inputPanelController){
		this.board = board;
		this.inputPanel = inputPanel;
		this.model = model;
		this.inputPanelController = inputPanelController;
		
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
					selectedButton.setSelected(true);
				//  debugging messages
				//	System.out.println("You selected " + board.getSelectedCell().getRow() + " " + board.getSelectedCell().getCol());
				}else{
					board.getSelectedButton().setSelected(false);
					board.setSelectedCell(selectedButton.getPosition());
					selectedButton.setSelected(true);
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
				int row = cb.getPosition().getRow();
				int col = cb.getPosition().getCol();
				cb.setCandidateLabel(model.getCandidates(row, col));
				Position p = new Position(row, col);
				int curr = (model.getCellNumber(row, col));
				if (curr != 0) {
					board.getButton(p).setNumberLabel(Integer.toString(curr));
				}else{
					cb.clearNumberLabel();
				}
				board.getButton(p).setGiven(model.isCellGiven(row, col));
				board.getButton(p).setCorrect(model.isCellCorrect(row, col));
				board.getButton(p).setValid(model.isCellValid(row, col));
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
					inputPanelController.setSelectedCell(pressedKey, p);
					inputPanelController.showIncorrect();
					inputPanelController.showInvalid();
				}else{
					if(arg0.getKeyCode() == KeyEvent.VK_UP && p.getRow() != 0){
						board.getSelectedButton().setSelected(false);
						board.setSelectedCell(new Position(p.getRow()-1, p.getCol()));
						board.getSelectedButton().setSelected(true);
						System.out.println("up");
					}else if(arg0.getKeyCode() == KeyEvent.VK_DOWN && p.getRow() != 8){
						board.getSelectedButton().setSelected(false);
						board.setSelectedCell(new Position(p.getRow()+1, p.getCol()));
						board.getSelectedButton().setSelected(true);
						System.out.println("down");
					}else if(arg0.getKeyCode() == KeyEvent.VK_LEFT && p.getCol() != 0){
						board.getSelectedButton().setSelected(false);
						board.setSelectedCell(new Position(p.getRow(), p.getCol()-1));
						board.getSelectedButton().setSelected(true);
						System.out.println("left");
					}else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT && p.getCol() != 8){
						board.getSelectedButton().setSelected(false);
						board.setSelectedCell(new Position(p.getRow(), p.getCol()+1));
						board.getSelectedButton().setSelected(true);
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

