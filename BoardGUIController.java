import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class BoardGUIController {
	private BoardGUI board;
	
	public BoardGUIController(BoardGUI board){
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
}

