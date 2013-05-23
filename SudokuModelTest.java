import java.util.Scanner;

public class SudokuModelTest {
    public static void main(String[] args) {
        // Testing generating puzzles
        SudokuModel sudokuSolver = new SudokuModel();
        sudokuSolver.generatePuzzle();
        sudokuSolver.printAnswerGrid();
        sudokuSolver.printCurrentGrid();

        System.out.println("Another ");
        sudokuSolver.generatePuzzle();
        sudokuSolver.printAnswerGrid();
        sudokuSolver.printCurrentGrid();
        System.out.println("Another ");
        sudokuSolver.generatePuzzle();
        sudokuSolver.printAnswerGrid();
        sudokuSolver.printCurrentGrid();
        
        // Testing findSolution, setting and solve.
        /*
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
        sudokuSolver.findSolution();
                
        System.out.println("Unsolved grid");
        sudokuSolver.printCurrentGrid();
        
        System.out.println("Putting some numbers in");
        sudokuSolver.setCellNumber(0, 0, 3);
        sudokuSolver.setCellNumber(8, 1, 2);
        sudokuSolver.setCellNumber(4, 4, 4);
        sudokuSolver.printCurrentGrid();
        
        System.out.println("Trying to put a number in a cell that was given");
        sudokuSolver.setCellNumber(0, 1, 2);
        
        System.out.println("Reveal solution");
        sudokuSolver.revealSolution();
        sudokuSolver.printCurrentGrid();

        
        System.out.println("Compare to (hard-coded) solution");
        System.out.print(solution);*/
        
    }
}
