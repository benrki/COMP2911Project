import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class BoardGUIController {
	private BoardGUI board;
	
	public BoardGUIController(BoardGUI board){
		this.board = board;
	}
	
	public void addSelectCellListener(){
		for(ArrayList<CellButton> a : board.getButtons()){
			for(CellButton b : a){
				b.addActionListener(new SelectCell(board, b));
			}
		}
	}
}

	class SelectCell implements ActionListener {
		private BoardGUI board;
		private CellButton selectedButton;
		
		SelectCell(BoardGUI board, CellButton cell){
			this.board = board;
			this.selectedButton = cell;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// select tile make it yellow
			if(board.getSelectedButton() == null){
				board.setSelectedCell(selectedButton.getPosition());
				selectedButton.setBackground(Color.YELLOW);
				System.out.println("You selected " + board.getSelectedCell().getX() + " " + board.getSelectedCell().getY());
	
			}else{
				board.getSelectedButton().setBackground(Color.WHITE);
				board.setSelectedCell(selectedButton.getPosition());
				selectedButton.setBackground(Color.YELLOW);
				System.out.println("You selected " + board.getSelectedCell().getX() + " " + board.getSelectedCell().getY());
			}
		}
}

