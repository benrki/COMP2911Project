import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;



public class BoardPanelController {
	private BoardPanel board;
	
	public BoardPanelController(BoardPanel board){
		this.board = board;
		
		for(ArrayList<CellButton> a : board.getButtons()){
			for(CellButton b : a){
				board.addActionListener(new SelectCell(b), b);
				board.addKeyListener(new KeyPress(board), b);
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
				int x = cb.getPosition().getX();
				int y = cb.getPosition().getY();
				Position position = new Position(x,y);
				int curr = (model.getCellNumber(x, y));
				if(curr != 0){
					board.getButton(position).setText(Integer.toString(curr));
				}
				board.getButton(position).setFont(new Font("sansserif",Font.BOLD,36));
			}
		}
	}
	
	class KeyPress implements KeyListener {
		
		private BoardPanel board;
		private String label;
		private Font font;
	
		public KeyPress(BoardPanel board){
			this.board = board;
			this.label = "123456789";
			this.font = new Font("sansserif",Font.BOLD,36);
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
		}

		@Override
		public void keyReleased(KeyEvent arg0) {	
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			String pressedKey = KeyEvent.getKeyText(arg0.getKeyChar());
			if(label.contains(pressedKey)){
				if(board.getSelectedCell() != null){
					board.getSelectedButton().setText(KeyEvent.getKeyText(arg0.getKeyChar()));
					board.getSelectedButton().setFont(font);
				}
			}
		}
	}
}

