import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[][] sudokuGrid = new int[8][8];
        for(int i = 0; i<8;i++){
            for(int j = 0;j<8;j++){
                sudokuGrid[i][j] = scanner.nextInt();
            }
            System.out.println();
        }

        SudokuSolver sudokuSolver = new SudokuSolver(sudokuGrid);
        sudokuSolver.solveSudoku();
        for(int i = 0; i<8;i++){
            for(int j = 0;j<8;j++){
                System.out.print(sudokuGrid[i][j]+" ");
            }
            System.out.println();
        }
    }
}
