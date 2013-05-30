import java.util.ArrayList;
 
public class Cell {
   
    // To do:
    // Reorder methods
    // Fix documentation
<<<<<<< HEAD
    
=======
   
>>>>>>> 9034fd60d8f3570373ddc4ce0e2bd0a01c6613ff
    public final static int EMPTY = 0;
    public final static int MIN_NUM = 1;
    public final static int MAX_NUM = 9;
    private final int row;
    private final int column;
    private int number;
    private boolean given; // If this cell was "given" (i.e You can't change the guess)
    private ArrayList<Integer> candidates;
   
    /**
     * Constructs a Cell.
     * @param row The row position of the cell.
     * @param column The column position of the cell
     */
    public Cell (int row, int column) {
        this.row = row;
        this.column = column;
        this.number = Cell.EMPTY;
        this.given = false;
        candidates = new ArrayList<Integer>();
    }
 
    /**
     * Gives the Cell the number. Sets the Cell as given.
     * @param number The number to give the Cell.
     */
    public void giveNumber(int number) {
        if( (Cell.MIN_NUM <= number) && (number <= Cell.MAX_NUM)) {
            this.number = number;
            this.given = true;
        }
    }
<<<<<<< HEAD
    
=======
   
>>>>>>> 9034fd60d8f3570373ddc4ce0e2bd0a01c6613ff
    /**
     * Remove the Cell number. Sets the Cell as not given.
     */
    public void removeNumber() {
        this.number = EMPTY;
        this.given = false;
    }
<<<<<<< HEAD
    
=======
   
>>>>>>> 9034fd60d8f3570373ddc4ce0e2bd0a01c6613ff
    /**
     * Sets the Cell number. (Only if it's not a given cell).
     * @param number The number to set the Cell number to.
     */
    public void setNumber(int number) {
        if(!this.given && (Cell.MIN_NUM <= number) && (number <= Cell.MAX_NUM)) {
            this.number = number;
        }
    }
 
    /**
     * Clears the Cell number. (Only if it's not a given cell).
     */
    public void clearNumber() {
        if(!this.given) {
            this.number = EMPTY;
        }
    }
<<<<<<< HEAD
    
    
=======
   
   
>>>>>>> 9034fd60d8f3570373ddc4ce0e2bd0a01c6613ff
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
        if(!this.candidates.contains(number) && (Cell.MIN_NUM <= number) && (number <= Cell.MAX_NUM)) {
            for(int i=0; i<candidates.size(); i++) {
                if(number < this.candidates.get(i)) {
                    this.candidates.add(i, number);
                    return;
                }
            }
            // Hasn't added candidate yet (i.e hasn't returned)
            this.candidates.add(number);
        }
    }
   
    /**
     * Removes the number specified as a candidate to this Cell.
     * @param number The candidate to this cell.
     */
    public void removeCandidate(int number) {
        if(this.candidates.contains(number)) {
            this.candidates.remove(this.candidates.indexOf(number));
        }
    }
   
    /**
     * Returns <tt>true</tt> if the number is a candidate to this Cell, <tt>false</tt> otherwise.
     * @param number The candidate to this cell.
     * @return <tt>true</tt> if the number is a candidate to this Cell, <tt>false</tt> otherwise.
     */
    public boolean hasCandidate(int number) {
        return this.candidates.contains(number);
    }
<<<<<<< HEAD
    
=======
   
>>>>>>> 9034fd60d8f3570373ddc4ce0e2bd0a01c6613ff
    /**
     * Returns an ordered ArrayList of the candidates.
     * @param number The candidate to this cell.
     * @return an ordered ArrayList of the candidates.
     */
    public ArrayList<Integer> getCandidates() {
        return this.candidates;
    }
}
