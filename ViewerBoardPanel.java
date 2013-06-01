import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * @author Ben
 *
 */
public class ViewerBoardPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static double DEFAULT_WEIGHT = 1;
	final static int NO_OF_SQ = 9;
	final static int NO_OF_ROWS_IN_SQ = 3;
	final static int NO_OF_COLUMNS_IN_SQ = 3;
	
	private ArrayList<ArrayList<ViewerCellButton>> buttons;
	private Position selectedCell;
	
	/**
	 * 
	 */
	public ViewerBoardPanel(){
		super();
		GridLayout layout = new GridLayout(NO_OF_ROWS_IN_SQ, NO_OF_COLUMNS_IN_SQ, 5, 5);
		buttons = new ArrayList<ArrayList<ViewerCellButton>>();
		this.setLayout(layout);
		this.setBackground(Color.BLACK);
		selectedCell = null;
		
		for(int h = 0; h < NO_OF_SQ; h++) {
			buttons.add(new ArrayList<ViewerCellButton>());
		}
		
		for(int i = 0; i < NO_OF_SQ; i++) {
			JPanel square = new JPanel(new GridLayout(NO_OF_ROWS_IN_SQ, NO_OF_COLUMNS_IN_SQ));
			int row;
			for(int j = 0; j < NO_OF_COLUMNS_IN_SQ; j++){
				for(int k = 0; k < NO_OF_ROWS_IN_SQ; k++){
					if(i < 3){
						row = j;
					} else if (i < 6){
						row = j+3;
					} else {
						row = j+6;
					}
					ViewerCellButton cell = new ViewerCellButton(new Position(row, buttons.get(row).size()));
					cell.setBackground(Color.WHITE);
					buttons.get(row).add(cell);
					square.add(cell);
				}
			}
			this.add(square);
		}
	}
	
	public ArrayList<ArrayList<ViewerCellButton>> getButtons(){
		return buttons;
	}
	
	public Position getSelectedCell(){
		return selectedCell;
	}
	
	public void setSelectedCell(Position position){
		this.selectedCell = position;
	}
	
	public ViewerCellButton getSelectedButton(){
		if(selectedCell == null){
			return null;
		}else{
			return buttons.get(selectedCell.getRow()).get(selectedCell.getCol());
		}
	}
	
	public ViewerCellButton getButton(Position position) {
		return buttons.get(position.getRow()).get(position.getCol());
	}
	
	public void addActionListener(ActionListener listener, ViewerCellButton b){
		b.addActionListener(listener);
	}
	
	public void addKeyListener(KeyListener listener, JButton button){
		button.addKeyListener(listener);
	}

}
