import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;


/**
 * @author Ben
 *
 */
public class GridPanel extends JPanel {
	/**
	 * Default serialization.
	 */
	private static final long serialVersionUID = 1L;
	private static final int NUM_ROW = 9;
	private static final int NUM_COL = 9;
	private ArrayList<ArrayList<Cell>> board;

	/**
	 * 
	 */
	public GridPanel() {
		super();
		board = new ArrayList<ArrayList<Cell>>();
		this.setLayout(new GridLayout(9,9));	// maybe add gaps later
		for (int i = 0; i < NUM_COL; i++) {
			ArrayList<Cell> newRow = new ArrayList<Cell>();
			board.add(newRow);
			for (int j = 0; j < NUM_ROW; j++) {
				Cell newCell = new Cell();
				newRow.add(newCell);
				this.add(newCell);
			}
		}
		
	}
	
	/**
	 * @param board
	 */
	public GridPanel(ArrayList<ArrayList<Cell>> board) {
		super();
		this.board = new ArrayList<ArrayList<Cell>>();
		this.setLayout(new GridLayout(9,9));	// maybe add gaps later
		for (int i = 0; i < board.size(); i++) {
			ArrayList<Cell> newRow = new ArrayList<Cell>();
			this.board.add(newRow);
			for (int j = 0; j < board.get(0).size(); j++) {
				Cell newCell = board.get(i).get(j);
				newRow.add(newCell);
				this.add(newCell);
			}
		}
		
	}
	
}
