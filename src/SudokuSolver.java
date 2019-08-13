import java.util.ArrayList;

public class SudokuSolver {
    private int[][] matrix;
    private int vacantSpaces;
    private int length;

    public SudokuSolver(int[][] matrix){
        this.matrix = matrix;
        this.length = matrix[0].length;
        this.vacantSpaces = getVacantSpaces();
    }

    public void solveSudoku(){
        System.out.println("Solving Sudoku in progress...");
        System.out.println("This might take a few moments...");
        crossHatch();
    }

    private void crossHatch(){

        while(vacantSpaces > 0){
            for(int i=0;i<length;i+=3){
                for (int j=0;j<length;j+=3) {
                    analyzeNonet(new Position(i,j));
                }
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
        int maxX = gridPosition.getX() + 3;
        int maxY = gridPosition.getY() + 3;

        for (int n = 1; n <= 9; n++) {
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

        int maxX = gridPosition.getX() + 3;
        int maxY = gridPosition.getY() + 3;
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
