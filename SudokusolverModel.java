import java.util.ArrayList;


public class testCell {
	public static void main(String[] args) {
	  /* Cell test1 = new Cell(2, 5, 8, 6, false);
	   System.out.print(test1.getBox());
	   System.out.print(test1.isCorrect());
	   Cell test2 = new Cell(6, 6, 8, 8, true);
	   System.out.print(test2.getBox());
	   System.out.print(test2.isCorrect());
	   Cell test3 = new Cell(8, 5, 8, 8, true);
	   System.out.print(test3.getBox());
	   System.out.print(test3.isCorrect());*/
	   // Construct ArrayListArrayListCell
	   
	   // Print Cell.getBox() for every cell
	   ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>();
	   for(int i=0; i<9; i++) {
		   grid.add(new ArrayList<Cell>());
		   for(int j=0; j<9; j++) {
			   grid.get(i).add(new Cell(i, j, 0, 0, true));
		   }
	   }
	   
	   for(int i=0; i<9; i++) {
		   for(int j=0; j<9; j++) {
			   System.out.print(grid.get(i).get(j).getBox()+" ");
		   }
		   System.out.println();
	   }
	}
}
