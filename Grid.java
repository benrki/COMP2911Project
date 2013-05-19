import java.util.ArrayList;
import java.util.Scanner;
//*** TO IMPLEMENT
// IS VALID CHECKS FOR CURRENT (NOT ANSWER) FOR USER FEEDBACK
// Change variable names, need to distinguish between "Is current valid" and "Is answer valid" for cell/row/col/box
public class Grid {
    public static final int EMPTY = 0;
    public static final int NUM_ROWS = 9;
    public static final int NUM_COLS = 9;
    ArrayList<ArrayList<Cell>> grid;     

    public Grid (String input) {
        this.grid = new ArrayList<ArrayList<Cell>>();
        Scanner s = new Scanner(input);
        for(int i=0; i<NUM_ROWS; i++) {
            grid.add(new ArrayList<Cell>());
            for(int j=0; j<NUM_COLS; j++) {
                int n = s.nextInt();
                boolean given = (n!=EMPTY);
                grid.get(i).add(new Cell(i, j, n, n, given));
            }
        }
        s.close();
    }
    
    /*public void setCellCurrent(int row, int col, int n) {
       this.grid.get(row).get(col).setCurrent(n);
    }

    public void setCellAnswer(int row, int col, int n) {
        this.grid.get(row).get(col).setAnswer(n);
    }*/
    
    public boolean isGridValid() {
       for(int i=0; i<NUM_ROWS; i++) {
           if(!isRowValid(i) || !isColumnValid(i) || !isBoxValid(i)) {
              return false;
           }
       }
       return true;
    }

    public boolean isCellValid(Cell c) {
       int row = c.getRow();
       int col = c.getColumn();
       int box = c.getBox();

       if(!isRowValid(row) || !isColumnValid(col) || !isBoxValid(box)) {
          return false;
       }
       return true;
    }
    
    public boolean isRowValid(int n) {
       ArrayList<Integer> row = new ArrayList<Integer>();
       for(int i=0; i<NUM_COLS; i++) {
          if(!row.contains(grid.get(n).get(i).getAnswer())) {
              if(grid.get(n).get(i).getAnswer()!=EMPTY) {
                  row.add(grid.get(n).get(i).getAnswer());
              }
          } else {
             return false;
          }
       }
       return true;
    }
    
    public boolean isColumnValid(int n) {
       ArrayList<Integer> col = new ArrayList<Integer>();
       for(int i=0; i<NUM_COLS; i++) {
          if(!col.contains(grid.get(i).get(n).getAnswer())) {
             if(grid.get(i).get(n).getAnswer()!=EMPTY) {
                col.add(grid.get(i).get(n).getAnswer());
             }
          } else {
             return false;
          }
       }
       return true;
    
    }
    
    public boolean isBoxValid(int n) {
       int row = (n/3)*3;
       int col = (n%3)*3;
       ArrayList<Integer> box = new ArrayList<Integer>();
       for(int i=row; i<row+3; i++) {
           for(int j=col; j<col+3; j++) {
               if(!box.contains(grid.get(i).get(j).getAnswer())) {
                   if(grid.get(i).get(j).getAnswer()!=EMPTY) {
                      box.add(grid.get(i).get(j).getAnswer());
                   }
               } else {
                   return false;
               }
           }
       }
       return true;
    }

    public void printGrid() {
        for(int i=0; i<NUM_ROWS; i++) {
            if(i%3==0&&i!=0) {
                System.out.println();
            }
            for(int j=0; j<NUM_COLS; j++) {
                if(j%3==0&&j!=0) {
                    System.out.print("| ");
                }
                if(this.grid.get(i).get(j).getCurrent()==EMPTY){
                    System.out.print("|-");
                } else {
                    System.out.print("|" + this.grid.get(i).get(j).getCurrent());
                }
            }
            System.out.println("|");
        }
        System.out.println();
    }
    
    public void findAnswers() {
        
        int i=0;
        int j=0;
        boolean finished = false;
        while(!finished) {
            Cell current = grid.get(i).get(j);
            if(!current.isGiven()) {
                boolean advance = false;
                int k = current.getAnswer();
                while(!advance) {
                    k++;
                    if(k>9) {
                        current.setAnswer(0);
                        while(advance==false) {
                            Cell c = this.prevCell(this.grid.get(i).get(j));
                            i = c.getRow();
                            j = c.getColumn();
                            if(!this.grid.get(i).get(j).isGiven()) {
                                advance = true;
                            }   
                        }
                    } else {
                        current.setAnswer(k);
                        // No violations, go next ungiven
                        if(this.isCellValid(current)) {
                            advance = true;
                            Cell c = this.nextCell(current);
                            if(c!=null) {
                                i = c.getRow();
                                j = c.getColumn();
                            } else {
                                return;
                            }
                        }
                    }
                }
            } else {
                Cell c = this.nextCell(current);
                if(c!=null) {
                    i = c.getRow();
                    j = c.getColumn();
                } else {
                    return;
                }
            }
        }
    }
    
    public void solveGrid() {
        for(int i=0; i<NUM_ROWS; i++) {
            for(int j=0; j<NUM_COLS; j++) {
                int answer = this.grid.get(i).get(j).getAnswer();
                this.grid.get(i).get(j).setCurrent(answer);
            }
        }
    }

    
    public Cell nextCell(Cell c) {
        int row = c.getRow();
        int col = c.getColumn();
        int nextRow = row;
        int nextCol = col+1;
        if(col==8) {
            nextCol = 0;
            nextRow = row+1;
        }
        if(nextRow>8 || nextCol >8) {
            return null;
        } 
        return this.grid.get(nextRow).get(nextCol);
    }
    
    public Cell prevCell (Cell c) {
        int row = c.getRow();
        int col = c.getColumn();
        int prevRow = row;
        int prevCol = col-1;
        if(col==0) {
            prevCol = 8;
            prevRow = row-1;
        }
        if(prevRow>8 || prevCol >8) {
            return null;
        } 
        return this.grid.get(prevRow).get(prevCol);
    }
        
    // isCellCorrect()
    // isGridCorrect()
    // isRowCorrect()
    // isColumnCorrect()
    // isBoxCorrect()
    
    // Grid hasSolution() returns null or solution board
}
