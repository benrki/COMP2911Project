
public class GridTest {
    public static void main(String[] args) {
        
        String input = 
                "0 5 9 0 0 0 0 0 8\n" +
                "7 0 2 0 5 0 0 4 1\n" + 
                "0 0 0 3 2 8 0 0 0\n" +
                "0 4 0 1 0 0 7 6 5\n" +
                "0 0 5 7 0 2 8 0 0\n" +
                "9 7 3 0 0 5 0 1 0\n" +
                "0 0 4 2 6 7 0 0 0\n" +
                "8 3 0 0 1 0 2 0 4\n" +
                "1 0 0 8 0 0 5 9 0\n";
        
        String solution = 
                "3 5 9 4 7 1 6 2 8\n" +
                "7 8 2 9 5 6 3 4 1\n" +
                "4 6 1 3 2 8 9 5 7\n" +
                "2 4 8 1 9 3 7 6 5\n" +
                "6 1 5 7 4 2 8 3 9\n" +
                "9 7 3 6 8 5 4 1 2\n" +
                "5 9 4 2 6 7 1 8 3\n" +
                "8 3 6 5 1 9 2 7 4\n" + 
                "1 2 7 8 3 4 5 9 6\n";
        
        Grid grid = new Grid(input);
        System.out.println("Unsolved board");
        grid.printGrid();
        System.out.println("Solved board");
        grid.findAnswers();
        grid.solveGrid();
        grid.printGrid();
        
        System.out.println("Compare to solution");
        Grid grid2 = new Grid(solution);
        grid2.printGrid();
        
        
        // Testing the getBox method in cell
        /*Cell c0 = new Cell(0, 0, 0, 0, false);
        Cell c1 = new Cell(2, 3, 0, 0, false);
        Cell c2 = new Cell(1, 8, 0, 0, false);
        Cell c3 = new Cell(3, 2, 0, 0, false);
        Cell c4 = new Cell(4, 4, 0, 0, false);
        Cell c5 = new Cell(5, 7, 0, 0, false);
        System.out.println("c0's box is " + c0.getBox());
        System.out.println("c1's box is " + c1.getBox());
        System.out.println("c2's box is " + c2.getBox());
        System.out.println("c3's box is " + c3.getBox());
        System.out.println("c4's box is " + c4.getBox());
        System.out.println("c5's box is " + c5.getBox());*/
        
    }
}
