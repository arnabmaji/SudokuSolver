import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[][] sudokuGrid = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        SudokuSolver sudokuSolver = new SudokuSolver(sudokuGrid);
        sudokuSolver.solveSudoku();
        for(int i = 0; i<3;i++){
            for(int j = 0;j<3;j++){
                System.out.print(sudokuGrid[i][j]+" ");
            }
            System.out.println();
        }
    }
}
