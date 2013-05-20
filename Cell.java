import java.util.ArrayList;
 
public class Cell {
    // If you want to change any variable names just tell me
    private final static int EMPTY = 0;
    private final int row;
    private final int column;
    private int current; // The number the user will see on the grid or what the user guessed (we need to define what blank is)
    private int answer; // The correct answer for the cell
    private final boolean given; // If this cell was "given" (i.e You can't change the guess)
    private ArrayList<Boolean> candidates;
   
    public Cell (int row, int column, int current, int answer, boolean given) {
        this.row = row;
        this.column = column;
        this.current = current;
        this.answer = answer;
        this.given = given;
        candidates = new ArrayList<Boolean>();
        for(int i=0; i<9; i++) {
           candidates.add(false);
        }
    }
 
    public void setCurrent(int current) {
        this.current = current;
    }
   
    public void setAnswer(int answer) {
        this.answer = answer;
    }    
 
    public int getCurrent() {
        return this.current;
    }
   
    public int getAnswer() {
        return this.answer;
    }
 
    public int getRow() {
        return this.row;
    }
   
    public int getColumn() {
        return this.column;
    }    
   
    public int getBox() {
     return 3*(this.row/3) + (this.column/3);
     }
 
    // returns given
    public boolean isGiven() {
        return this.given;
    }
   
    // if the current and answer are the same
    public boolean isCorrect() {
        if (this.current == this.answer) {
            return true;
        }
        return false;
    }
    public void addCandidate(int n) {
       this.candidates.set(n-1, true);
    }
   
    public void removeCandidate(int n) {
        this.candidates.set(n-1, false);
    }
   
    public boolean hasCandidate(int n) {
       return candidates.get(n-1);
    }
}