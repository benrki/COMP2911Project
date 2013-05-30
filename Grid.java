import java.util.ArrayList;

public class Grid {
    
    // To do:
    // Reorder methods
    // Fix documentation
    
    public static final int EMPTY = 0;
    public static final int NUM_ROWS = 9;
    public static final int NUM_COLS = 9;
    ArrayList<ArrayList<Cell>> grid;     
    public static final String newline = System.getProperty("line.separator");
    /**
     * Constructs a Grid.
     */
    public Grid () {
        this.grid = new ArrayList<ArrayList<Cell>>();
        for(int i=0; i<NUM_ROWS; i++) {
            grid.add(new ArrayList<Cell>());
            for(int j=0; j<NUM_COLS; j++) {
                grid.get(i).add(new Cell(i, j));
            }
        }
    }

    /**
     * Returns the Cell in row "row" and column "col".
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @return the Cell in row "row" and column "col".
     */
    public Cell getCell(int row, int col) {
        return this.grid.get(row).get(col);
    }
    
    /**
     * Returns <tt>true</tt> if Cell is valid, false otherwise.
     * @return <tt>true</tt> if Cell is valid, false otherwise.
     */
    public boolean isCellValid(int row, int col) {
       int box = getCell(row, col).getBox();
       if(!isRowValid(row) || !isColumnValid(col) || !isBoxValid(box)) {
          return false;
       }
       return true;
    }
    
    /**
     * Returns <tt>true</tt> if Grid is valid, false otherwise.
     * @return <tt>true</tt> if Grid is valid, false otherwise.
     */
    public boolean isGridValid() {
       for(int i=0; i<NUM_ROWS; i++) {
           if(!isRowValid(i) || !isColumnValid(i) || !isBoxValid(i)) {
              return false;
           }
       }
       return true;
    }

    
    /**
     * Returns <tt>true</tt> if Row is valid, false otherwise.
     * @return <tt>true</tt> if Row is valid, false otherwise.
     */
    public boolean isRowValid(int n) {
       ArrayList<Integer> row = new ArrayList<Integer>();
       for(int i=0; i<NUM_COLS; i++) {
          if(!row.contains(grid.get(n).get(i).getNumber())) {
              if(grid.get(n).get(i).getNumber()!=EMPTY) {
                  row.add(grid.get(n).get(i).getNumber());
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
          if(!col.contains(grid.get(i).get(n).getNumber())) {
             if(grid.get(i).get(n).getNumber()!=EMPTY) {
                col.add(grid.get(i).get(n).getNumber());
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
               if(!box.contains(grid.get(i).get(j).getNumber())) {
                   if(grid.get(i).get(j).getNumber()!=EMPTY) {
                      box.add(grid.get(i).get(j).getNumber());
                   }
               } else {
                   return false;
               }
           }
       }
       return true;
    }
    
    // Used only for testing
    public void printGrid() {
        for(int i=0; i<NUM_ROWS; i++) {
            if(i%3==0&&i!=0) {
                System.out.println();
            }
            for(int j=0; j<NUM_COLS; j++) {
                if(j%3==0&&j!=0) {
                    System.out.print("| ");
                }
                if(this.grid.get(i).get(j).getNumber()==EMPTY){
                    System.out.print("|-");
                } else {
                    System.out.print("|" + this.grid.get(i).get(j).getNumber());
                }
            }
            System.out.println("|");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        String sudoku = "";
    	for (int i=0; i<NUM_ROWS; i++) {
    		for (int j=0; j<NUM_COLS; j++) {
    			sudoku = sudoku + grid.get(i).get(j).getNumber() + " ";
    		}
    		sudoku = sudoku + newline;
    	}
    	return sudoku;
    }
}
