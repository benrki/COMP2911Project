import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



public class BoardPanelController {
	private BoardPanel board;
	
	public BoardPanelController(BoardPanel board){
		this.board = board;
		
		for(ArrayList<CellButton> a : board.getButtons()){
			for(CellButton b : a){
				board.addActionListener(new SelectCell(b), b);
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
				// debugging messages
				//	System.out.println("You selected " + board.getSelectedCell().getX() + " " + board.getSelectedCell().getY());
		
				}else{
					board.getSelectedButton().setBackground(Color.WHITE);
					board.setSelectedCell(selectedButton.getPosition());
					selectedButton.setBackground(Color.YELLOW);
				// debugging messages
				//	System.out.println("You selected " + board.getSelectedCell().getX() + " " + board.getSelectedCell().getY());
				}
			} catch (NullPointerException o){
				System.out.println("sumting wong");
			}
		}
	}
	
	public void setCell(Position position) {
		board.setSelectedCell(position);
	}
	
	public void setBoard(SudokuModel model) {
		for (ArrayList<CellButton> cbList: board.getButtons()) {
			for (CellButton cb : cbList) {
				cb.clearLabel();
				int x = cb.getPosition().getX();
				int y = cb.getPosition().getY();
				Position p = new Position(x, y);
				int curr = (model.getCellNumber(x, y));
				if (curr != 0) {
					board.getButton(p).setText(Integer.toString(curr));
				}
				board.getButton(p).setFont(new Font("sansserif",Font.BOLD,36));
			}
		}
	}
	
	public void solve(SudokuModel model) {
		model.revealSolution();
		this.setBoard(model);
	}
	
}

