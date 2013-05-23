import java.util.Random;
import java.util.Scanner;

public class SudokuGenerator {
    //http://zhangroup.aporc.org/images/files/Paper_3485.pdf
    private Grid currentGrid;
    private Grid answerGrid;
    private Random random;
    
    public SudokuGenerator (Grid currentGrid, Grid answerGrid) {
        this.currentGrid = currentGrid;
        this.answerGrid = this.initialiseAnswerGrid(answerGrid);
        this.random = new Random();
    }
    
    public void generate() {
        this.gridShuffle();
        this.duplicateAnswerGrid();
        this.removeCells();
    }
    private void duplicateAnswerGrid() {
        for(int i=0; i<Grid.NUM_ROWS; i++) {
            for(int j=0; j<Grid.NUM_COLS; j++) {
                currentGrid.getCell(i, j).setNumber(answerGrid.getCell(i, j).getNumber());
                currentGrid.getCell(i, j).setGiven(true);
            }
        }
    }
    private Grid initialiseAnswerGrid(Grid answerGrid) {
        String input = 
                "3 5 9 4 7 1 6 2 8\n" +
                "7 8 2 9 5 6 3 4 1\n" +
                "4 6 1 3 2 8 9 5 7\n" +
                "2 4 8 1 9 3 7 6 5\n" +
                "6 1 5 7 4 2 8 3 9\n" +
                "9 7 3 6 8 5 4 1 2\n" +
                "5 9 4 2 6 7 1 8 3\n" +
                "8 3 6 5 1 9 2 7 4\n" + 
                "1 2 7 8 3 4 5 9 6\n";
        Scanner s = new Scanner(input);
        for(int i=0; i<Grid.NUM_ROWS; i++) {
            for(int j=0; j<Grid.NUM_COLS; j++) {
                int n = s.nextInt();
                answerGrid.getCell(i, j).setNumber(n);
                answerGrid.getCell(i, j).setGiven(true);
            }
        }
        s.close();
        return answerGrid;
    }
    
    private void removeCells() {
        int remove = 20;
        for(int i=0; i<remove; i++) {
            this.removeRandomCellInBox(i%9);
        }
    }
    private void removeRandomCellInBox(int box) {
        // Position of top-left cell in Box "box"
        int boxRow = 3*(box/3);
        int boxCol = 3*(box%3);
        int row = boxRow + random.nextInt(3);
        int col = boxCol + random.nextInt(3);
        while(this.currentGrid.getCell(row, col).getNumber()==Grid.EMPTY) {
            row = boxRow + random.nextInt(3);
            col = boxCol + random.nextInt(3);
        }
        this.currentGrid.getCell(row, col).setNumber(Grid.EMPTY);    
        this.currentGrid.getCell(row, col).setGiven(false);
    }
    private void gridShuffle() {
        for(int i=0; i<50; i++) {
            int method = random.nextInt(7)+1;
            this.doMethod(method);
        }
        this.doMethod(1);
    }
    
    private void doMethod(int n) {
        if(n==1) {
            this.exchangeRandomDigits();
        } else if (n==2) {
            this.exchangeRandomColumn();
        } else if (n==3) {
            this.exchangeRandomBlockColumn();
        } else if (n==4) {
            this.exchangeRandomRow();
        } else if (n==5) {
            this.exchangeRandomBlockRow();
        } else if (n==6) {
            this.rotateLeft();
        } else if (n==7) {
            this.rotateRight();
        } 
    }
    // Exchanges the positions of every occurence of two random digits.
    private void exchangeRandomDigits() {
        int one = random.nextInt(9)+1;
        int two = one;
        while(two==one) {
            two = random.nextInt(9)+1;
        }
        
        for(int i=0; i<Grid.NUM_ROWS; i++) {
            for(int j=0; j<Grid.NUM_COLS; j++) {
                if(answerGrid.getCell(i, j).getNumber()==one) {
                    answerGrid.getCell(i, j).setNumber(two);
                } else if (answerGrid.getCell(i, j).getNumber()==two) {
                    answerGrid.getCell(i, j).setNumber(one);
                }
            }
        }
        
    }
    
    // Exchanges two random columns that can be exchanged
    private void exchangeRandomColumn () {
        int one = random.nextInt(9);
        int two = one;
        int tmp;
        while(two==one) {
            two = one/3 + random.nextInt(3);
        }
        for(int i=0; i<Grid.NUM_ROWS; i++) {
            tmp = answerGrid.getCell(i, one).getNumber();
            answerGrid.getCell(i, one).setNumber(answerGrid.getCell(i, two).getNumber());
            answerGrid.getCell(i, two).setNumber(tmp);
        }
    }
    
    // Exchanges a column of blocks with another column of blocks
    private void exchangeRandomBlockColumn() {
        int one = random.nextInt(3);
        int two = one;
        int tmp;
        while(two==one) {
            two = random.nextInt(3);
        }
        for(int i=0; i<Grid.NUM_ROWS; i++) {
            for(int j=0; j<3; j++) {
                tmp = answerGrid.getCell(i, one+j).getNumber();
                answerGrid.getCell(i, one+j).setNumber(answerGrid.getCell(i, two+j).getNumber());
                answerGrid.getCell(i, two+j).setNumber(tmp);
            }
        }
        
    }
    
    private void exchangeRandomRow () {
        int one = random.nextInt(9);
        int two = one;
        int tmp;
        while(two==one) {
            two = one/3 + random.nextInt(3);
        }
        for(int i=0; i<Grid.NUM_COLS; i++) {
            tmp = answerGrid.getCell(one, i).getNumber();
            answerGrid.getCell(one, i).setNumber(answerGrid.getCell(two, i).getNumber());
            answerGrid.getCell(two, i).setNumber(tmp);
        }  
    }
    
    private void exchangeRandomBlockRow() {
        int one = random.nextInt(3);
        int two = one;
        int tmp;
        while(two==one) {
            two = random.nextInt(3);
        }
        for(int i=0; i<Grid.NUM_COLS; i++) {
            for(int j=0; j<3; j++) {
                tmp = answerGrid.getCell(one+j, i).getNumber();
                answerGrid.getCell(one+j, i).setNumber(answerGrid.getCell(two+j, i).getNumber());
                answerGrid.getCell(two+j, i).setNumber(tmp);
            }
        }
    }
    
    private void rotateLeft() {
        // TO do
    }
    
    private void rotateRight() {
        // To do
    }
}
