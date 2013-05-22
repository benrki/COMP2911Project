<<<<<<< HEAD
import javax.swing.JButton;


/**
 * @author Ben
 *
 */
public class Cell extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Cell() {
		super("0");
	}

}
=======
import java.util.ArrayList;
 
public class Cell {
    
    // Fix documentation in every class to make things consistent.
    // Use consistent parameters (col/column n/number)
    private final static int EMPTY = 0;
    private final int row;
    private final int column;
    private int number; 
    private boolean given; // If this cell was "given" (i.e You can't change the guess)
    private ArrayList<Boolean> candidates;
   
    /**
     * Constructs a Cell.
     * @param row The row position of the cell.
     * @param column The column position of the cell
     */
    public Cell (int row, int column) {
        this.row = row;
        this.column = column;
        this.number = EMPTY;
        this.given = false;
        candidates = new ArrayList<Boolean>();
        for(int i=0; i<9; i++) {
           candidates.add(false);
        }
    }
 
    /**
     * Sets the Cell number.
     * @param number The number to set the Cell number to.
     */
    public void setNumber(int number) {
        //if(!this.given && 0 <= number && number <= 9) {
            this.number = number;
        //}
    }
 
    /**
     * Sets the given to true/false.
     * @param given <tt>true</tt> or <tt>false</tt>
     */
    public void setGiven (boolean given) {
        this.given = given;
    }
    
    /**
     * Returns the number of the Cell.
     * @return the number of the Cell.
     */
    public int getNumber() {
        return this.number;
    }
   
    /**
     * Returns the row position of the Cell.
     * @return the row position of the Cell.
     */
    public int getRow() {
        return this.row;
    }
   
    /**
     * Returns the column position of the Cell.
     * @return the column position of the Cell.
     */
    public int getColumn() {
        return this.column;
    }    
   
    /**
     * Returns the box the Cell belongs to.
     * @return the box the Cell belongs to.
     */
    public int getBox() {
     return 3*(this.row/3) + (this.column/3);
     }
 
    /**
     * Returns <tt>true</tt> if the Cell is a "given" Cell, <tt>false</tt> otherwise.
     * @return <tt>true</tt> if the Cell is a "given" Cell, <tt>false</tt> otherwise.
     */
    public boolean isGiven() {
        return this.given;
    }
   
    /**
     * Adds the number specified as a candidate to this Cell.
     * @param number The candidate to this cell.
     */
    public void addCandidate(int number) {
       this.candidates.set(number-1, true);
    }
   
    /**
     * Removes the number specified as a candidate to this Cell.
     * @param number The candidate to this cell.
     */
    public void removeCandidate(int number) {
        this.candidates.set(number-1, false);
    }
   
    /**
     * Returns <tt>true</tt> if the number is a candidate to this Cell, <tt>false</tt> otherwise.
     * @param number The candidate to this cell.
     * @return <tt>true</tt> if the number is a candidate to this Cell, <tt>false</tt> otherwise.
     */
    public boolean hasCandidate(int number) {
       return candidates.get(number-1);
    }
}
>>>>>>> 6b2d3fecfd0138fa918617e2c29f21dbacd08160
