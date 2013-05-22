import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;


public class BoardGUI {
	final static double DEFAULT_WEIGHT = 1;
	final static int NO_OF_SQ = 9;
	final static int NO_OF_ROWS_IN_SQ = 3;
	final static int NO_OF_COLUMNS_IN_SQ = 3;
	
	private JPanel board;
	private ArrayList<ArrayList<CellButton>> buttons;
	private Position selectedCell;
	
	public BoardGUI(){
		GridLayout layout = new GridLayout(NO_OF_ROWS_IN_SQ, NO_OF_COLUMNS_IN_SQ, 5, 5);
		buttons = new ArrayList<ArrayList<CellButton>>();
		this.board = new JPanel(layout);
		board.setBackground(Color.BLACK);
		selectedCell = null;
		for(int h = 0; h < NO_OF_SQ; h++){
			buttons.add(new ArrayList<CellButton>());
		}
		for(int i = 0; i < NO_OF_SQ; i++){
			JPanel square = new JPanel(new GridLayout(NO_OF_ROWS_IN_SQ, NO_OF_COLUMNS_IN_SQ));
			int row;
			for(int j = 0; j < NO_OF_COLUMNS_IN_SQ; j++){
				for(int k = 0; k < NO_OF_ROWS_IN_SQ; k ++){
					if(i < 3){
						row = j;
					} else if (i < 6){
						row = j+3;
					} else {
						row = j+6;
					}
					
					CellButton cell = new CellButton(new Position(row, buttons.get(row).size()));
					cell.setBackground(Color.WHITE);
					buttons.get(row).add(cell);
					square.add(cell);
				}
			}
			board.add(square);
		}
	}
	
	public JPanel getBoard(){
		return board;
	}
	
	public ArrayList<ArrayList<CellButton>> getButtons(){
		return buttons;
	}
	
	public Position getSelectedCell(){
		return selectedCell;
	}
	
	public void setSelectedCell(Position position){
		this.selectedCell = position;
	}
	
	public JButton getSelectedButton(){
		if(selectedCell == null){
			return null;
		}else{
			return buttons.get(selectedCell.getX()).get(selectedCell.getY());
		}
	}

}
