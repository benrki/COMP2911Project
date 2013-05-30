import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;



public class BoardPanelController {
	private BoardPanel board;
	private InputPanel inputPanel;
	private SudokuModel model;
	
	public BoardPanelController(BoardPanel board, SudokuModel model, InputPanel inputPanel){
		this.board = board;
		this.inputPanel = inputPanel;
		this.model = model;
		
		for(ArrayList<CellButton> a : board.getButtons()){
			for(CellButton b : a){
				board.addActionListener(new SelectCell(b), b);
				board.addKeyListener(new KeyPress(b), b);
			}
		}
	}

	class SelectCell implements ActionListener {
		private CellButton selectedButton;
		
		SelectCell(CellButton cell){
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
				//	System.out.println("You selected " + board.getSelectedCell().getX() + " " + board.getSelectedCell().getY());
		
				}else{
					board.getSelectedButton().setBackground(Color.WHITE);
					board.setSelectedCell(selectedButton.getPosition());
					selectedButton.setBackground(Color.YELLOW);
				//  debugging messages
				//	System.out.println("You selected " + board.getSelectedCell().getX() + " " + board.getSelectedCell().getY());
				}
			} catch (NullPointerException o){
				System.out.println("sumting wong");
			}
		}
	}
	
	public void updateBoard() {
		for (ArrayList<CellButton> cbList: board.getButtons()) {
			for (CellButton cb : cbList) {
				cb.clearNumberLabel();
				cb.clearCandidateLabel();
				int x = cb.getPosition().getX();
				int y = cb.getPosition().getY();
				Position p = new Position(x, y);
				int curr = (model.getCellNumber(x, y));
				if (curr != 0) {
					board.getButton(p).setNumberLabel(Integer.toString(curr));
				}
				if(model.isCellGiven(p.getX(), p.getY())){
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
					if (!model.isCellGiven(p.getX(), p.getY())) {
						if(!inputPanel.getCandidateButton().isSelected()){
							model.setCellNumber(p.getX(), p.getY(), Integer.parseInt(pressedKey));
							board.getSelectedButton().setNumberLabel(pressedKey);
							if (model.isSudokuFinished()) {
								JOptionPane.showMessageDialog(null, "Trophy 4 u");
							}
						}else{
							if(model.hasCellCandidate(p.getX(), p.getY(), Integer.parseInt(pressedKey))){
							//	System.out.println(label);
								model.removeCellCandidate(p.getX(), p.getY(), Integer.parseInt(pressedKey));
								board.getSelectedButton().setCandidateLabel(model.getCandidates(p.getX(), p.getY()));
							}else{
							//	System.out.println(label);
								model.addCellCandidate(p.getX(), p.getY(), Integer.parseInt(pressedKey));
								board.getSelectedButton().setCandidateLabel(model.getCandidates(p.getX(), p.getY()));
							}
						}
					}
				}else{
					if(arg0.getKeyCode() == KeyEvent.VK_UP && p.getX() != 0){
						board.getSelectedButton().setBackground(Color.WHITE);
						board.setSelectedCell(new Position(p.getX()-1, p.getY()));
						board.getSelectedButton().setBackground(Color.YELLOW);
						System.out.println("up");
					}else if(arg0.getKeyCode() == KeyEvent.VK_DOWN && p.getX() != 8){
						board.getSelectedButton().setBackground(Color.WHITE);
						board.setSelectedCell(new Position(p.getX()+1, p.getY()));
						board.getSelectedButton().setBackground(Color.YELLOW);
						System.out.println("down");
					}else if(arg0.getKeyCode() == KeyEvent.VK_LEFT && p.getY() != 0){
						board.getSelectedButton().setBackground(Color.WHITE);
						board.setSelectedCell(new Position(p.getX(), p.getY()-1));
						board.getSelectedButton().setBackground(Color.YELLOW);
						System.out.println("left");
					}else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT && p.getY() != 8){
						board.getSelectedButton().setBackground(Color.WHITE);
						board.setSelectedCell(new Position(p.getX(), p.getY()+1));
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

