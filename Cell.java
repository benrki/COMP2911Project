import java.util.ArrayList;
 
public class Cell {
    
    private final static int EMPTY = 0;
    private final int row;
    private final int column;
    private int number; 
    private boolean given; // If this cell was "given" (i.e You can't change the guess)
    private ArrayList<Boolean> candidates;
   
    public Cell (int row, int column, int number) {
        this.row = row;
        this.column = column;
        this.number = number;
        this.given = false;
        candidates = new ArrayList<Boolean>();
        for(int i=0; i<9; i++) {
           candidates.add(false);
        }
    }
 
    public void setNumber(int number) {
        if(!this.given) {
            this.number = number;
        }
    }
 
    public void setGiven (boolean given) {
        this.given = given;
    }
    
    public int getNumber() {
        return this.number;
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
 
    public boolean isGiven() {
        return this.given;
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
    
    public boolean isCorrect(Cell answerCell) {
        if (this.getNumber() == answerCell.getNumber()) {
            return true;
        }
        return false;
    }
}