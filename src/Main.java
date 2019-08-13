import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] sudokuGrid = {
                {0,0,6,0,3,1,0,7,0},
                {4,3,7,0,0,5,0,0,0},
                {0,1,0,4,6,7,0,0,8},
                {0,2,9,1,7,8,3,0,0},
                {0,0,0,0,0,0,0,2,6},
                {3,0,0,0,5,0,0,0,0},
                {8,0,5,0,0,4,9,1,0},
                {0,0,3,5,0,9,0,8,7},
                {7,9,0,0,8,6,0,0,4}
        };

        SudokuSolver sudokuSolver = new SudokuSolver(sudokuGrid);
        sudokuSolver.solveSudoku();
        for(int i = 0; i<9;i++){
            for(int j = 0;j<9;j++){
                System.out.print(sudokuGrid[i][j]+" ");
            }
            System.out.println();
        }
    }
}