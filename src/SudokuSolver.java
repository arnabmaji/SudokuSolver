import java.util.ArrayList;

public class SudokuSolver {
    private int[][] matrix;
    private int vacantSpaces;
    private int length;
    private int maxGridWidth;
    private int maxGridHeight;
    private int maxDigit;

    public SudokuSolver(int[][] matrix){
        this.matrix = matrix;
        verifySudokuFormat();
        this.vacantSpaces = getVacantSpaces();
    }

    private void verifySudokuFormat(){
        if (matrix.length == 9 && matrix[0].length == 9){
            this.length = matrix.length;
            maxGridHeight = 3;
            maxGridWidth = 3;
            maxDigit = 9;
        } else if(matrix.length == 8 && matrix[0].length == 8){
            this.length = matrix.length;
            maxGridHeight = 2;
            maxGridWidth = 4;
            maxDigit = 8;
        } else if(matrix.length == 6 && matrix[0].length == 6){
            this.length = matrix.length;
            maxGridHeight = 2;
            maxGridWidth = 3;
            maxDigit = 6;
        }
    }

    public void solveSudoku(){
        System.out.println("Solving Sudoku in progress...");
        System.out.println("This might take a few moments...");
        crossHatch();
    }

    private void crossHatch(){
        while(vacantSpaces > 0){
            int previusVacantSpaces = vacantSpaces;
            for(int i=0;i<length;i+= maxGridHeight){
                for (int j=0;j<length;j+= maxGridWidth) {
                    analyzeNonet(new Position(i,j));
                }
            }
            if(previusVacantSpaces == vacantSpaces){
                System.out.println("Probably it doesn't have any solution!");
                break;
            }
        }
    }

    private void analyzeNonet(Position gridPosition){
        ArrayList<Integer> missingNumbers = getMissingNumbers(gridPosition);
        boolean isNewNumberAdded = false;
        for(int number : missingNumbers){
            ArrayList<Position> availablePositions = getAvailablePositions(gridPosition,number);
            if(availablePositions.size() == 1){
                matrix[availablePositions.get(0).getX()][availablePositions.get(0).getY()] = number;
                vacantSpaces--;
                isNewNumberAdded = true;
            }
        }
        if(isNewNumberAdded){
            analyzeNonet(gridPosition);
        }

    }

    private ArrayList<Integer> getMissingNumbers(Position gridPosition) {
        ArrayList<Integer> missingNumbersList = new ArrayList<>();
        int maxX = gridPosition.getX() + maxGridHeight;
        int maxY = gridPosition.getY() + maxGridWidth;

        for (int n = 1; n <= maxDigit; n++) {
            boolean isFound = false;
            for (int i = gridPosition.getX(); i < maxX; i++) {
                for (int j = gridPosition.getY(); j < maxY; j++) {
                    if (n == matrix[i][j]) {
                        isFound = true;
                        break;
                    }
                }
                if(isFound){
                    break;
                }
            }
            if (!isFound) {
                missingNumbersList.add(n);
            }
        }
        return missingNumbersList;
    }

    private ArrayList<Position> getAvailablePositions(Position gridPosition, int number){
        ArrayList<Position> availablePositions = new ArrayList<>();

        int maxX = gridPosition.getX() + maxGridHeight;
        int maxY = gridPosition.getY() + maxGridWidth;
        for(int i=gridPosition.getX();i<maxX && availablePositions.size()<2;i++){
            for(int j=gridPosition.getY();j<maxY && availablePositions.size()<2;j++){
                if(matrix[i][j] == 0){
                    Position currentPosition = new Position(i,j);
                    if(!isAlreadyInThisRow(currentPosition,number) && !isAlreadyInThisColumn(currentPosition,number)){
                        availablePositions.add(currentPosition);
                    }
                }
            }
        }
        return availablePositions;
    }

    private boolean isAlreadyInThisRow(Position pos, int number){
        for(int i=0;i<length;i++){
            if(matrix[i][pos.getY()] == number){
                return true;
            }
        }
        return false;
    }

    private boolean isAlreadyInThisColumn(Position pos, int number){
        for(int i=0;i<length;i++){
            if(matrix[pos.getX()][i] == number){
                return true;
            }
        }
        return false;
    }

    private int getVacantSpaces(){
        int count = 0;
        for(int i=0;i<length;i++){
            for (int j=0;j<length;j++) {
                if(matrix[i][j] == 0){
                    count++;
                }
            }
        }
        return count;
    }
}
