import java.util.Scanner;

public class SudokuModelTest {
    public static void main(String[] args) {
        
        // Testing generating puzzles
        SudokuModel sudoku = new SudokuModel();
        //sudoku.generatePuzzle();
        System.out.println("Empty Grid");
        sudoku.printCurrentGrid();
        System.out.println("Putting numbers in (Should add 1..6");
        sudoku.setCellNumber(0, 0, 1);
        sudoku.setCellNumber(0, 1, 2);
        sudoku.setCellNumber(0, 2, 3);
        sudoku.setCellNumber(0, 3, 4);
        sudoku.setCellNumber(0, 4, 5);
        sudoku.setCellNumber(0, 5, 6);
        sudoku.printCurrentGrid();
        System.out.println("Undo two moves (Should remove 5, 6)");
        sudoku.undoMove();
        sudoku.undoMove();
        sudoku.printCurrentGrid();
        System.out.println("Redo two moves (Should add 5, 6)");
        sudoku.redoMove();
        sudoku.redoMove();
        sudoku.printCurrentGrid();
        System.out.println("Undo two moves (Should remove 5, 6)");
        sudoku.undoMove();
        sudoku.undoMove();
        sudoku.printCurrentGrid();
        System.out.println("Do a move (Should add 7)");
        sudoku.setCellNumber(0, 4, 7);
        sudoku.printCurrentGrid();
        System.out.println("Redo (should be nothing)");
        sudoku.redoMove();
        sudoku.printCurrentGrid();
        System.out.println("Undo (should remove 7)");
        sudoku.undoMove();
        sudoku.printCurrentGrid();      
        
        /*
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
        */
        
        // Testing findSolution, setting and solve.
    	/*
        String input = 
                "0 5 9 1 1 1 1 1 8\n" +
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
        
        SudokuModel sudoku = new SudokuModel();
        // Should print all blank first
        sudoku.printCurrentGrid();
        // Read input
        Scanner s = new Scanner(input);
        for(int i=0; i<Grid.NUM_ROWS; i++) {
            for(int j=0; j<Grid.NUM_COLS; j++) {
                int n = s.nextInt();
                if(n!=0) {
                    sudoku.giveCellNumber(i, j, n);
                }
            }
        }
        s.close();
        sudoku.findSolution();
                
        System.out.println("Unsolved grid");
        sudoku.printCurrentGrid();
        sudoku.printAnswerGrid();
        */
        /*System.out.println("Putting some numbers in");
        sudoku.setCellNumber(0, 0, 3);
        sudoku.setCellNumber(8, 1, 2);
        sudoku.setCellNumber(4, 4, 4);
        sudoku.printCurrentGrid();
        
        System.out.println("Trying to put a number in a cell that was given");
        sudoku.setCellNumber(0, 1, 2);
        
        System.out.println("Reveal solution");
        sudoku.revealSolution();
        sudoku.printCurrentGrid();
        sudoku.printAnswerGrid();

        
        System.out.println("Compare to (hard-coded) solution");
        System.out.print(solution);*/
        
    }
}
