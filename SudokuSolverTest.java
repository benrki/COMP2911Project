import java.util.Scanner;

public class SudokuSolverTest {
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
        
        SudokuSolver sudokuSolver = new SudokuSolver();
        // Should print all blank first
        sudokuSolver.printCurrentGrid();
        // Read input
        Scanner s = new Scanner(input);
        for(int i=0; i<Grid.NUM_ROWS; i++) {
            for(int j=0; j<Grid.NUM_COLS; j++) {
                int n = s.nextInt();
                if(n!=0) {
                    sudokuSolver.giveCellNumber(i, j, n);
                }
            }
        }
        s.close();
        System.out.println("Unsolved grid");
        sudokuSolver.printCurrentGrid();
        
        System.out.println("Putting some numbers in");
        sudokuSolver.setCurrentCellNumber(0, 0, 3);
        sudokuSolver.setCurrentCellNumber(8, 1, 2);
        sudokuSolver.setCurrentCellNumber(4, 4, 4);
        sudokuSolver.printCurrentGrid();
        
        System.out.println("Trying to put a number in a cell that was given");
        sudokuSolver.setCurrentCellNumber(0, 1, 2);
        
        System.out.println("Find answers");
        sudokuSolver.findAnswers();
        sudokuSolver.printCurrentGrid();
        
        System.out.println("Solve grid");
        sudokuSolver.solveGrid();
        sudokuSolver.printCurrentGrid();
        
        
        System.out.println("Compare to solution");
        System.out.print(solution);
        
    }
}
