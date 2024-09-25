public class SodokuSolver{
    //creates the size of the grid to 9 - standard sodoku size
    private static final  int GRID_SIZE = 9;
    public static void main(String [] args){
    
        int[][] board = {
        {7, 0, 2, 0, 5, 0, 6, 0, 0 },
        {0, 0, 0, 0, 0, 3, 0, 0, 0 },
        {1, 0, 0, 0, 0, 9, 5, 0, 0 },
        {8, 0, 0, 0, 0, 0, 0, 9, 0 },
        {0, 4, 3, 0, 0, 0, 7, 5, 0 },
        {0, 9, 0, 0, 0, 0, 0, 0, 8 },
        {0, 0, 9, 7, 0, 0, 0, 0, 5 },
        {0, 0, 0, 2, 0, 0, 0, 0, 0 },
        {0, 0, 7, 0, 4, 0, 2, 0, 3 },
        };

        printBoard(board); //printing board before solving
        System.out.println();

        //Printing whether it was able to solve the board or not
        if(solveBoard(board)){
            System.out.println("Solved successfully!\n");
        }else{
            System.out.println("Unsolvable board\n");
            }
         
        printBoard(board);//printing board after solving
    }

    //Method which goes through the numbers to print
    private static void printBoard(int[][] board){
        //for loop which goes through the rows
        for (int row = 0; row < GRID_SIZE; row++){
           
           //Printing lines to make it easier to read
            if(row % 3 == 0 && row != 0){
                System.out.println("-----------");
            }
            
            //going through the columns
            for(int column = 0; column < GRID_SIZE; column++){
            
                if(column % 3 == 0 && column != 0){
                    System.out.print ("|");
                }
                System.out.print(board[row][column]);//printing the number in the specific square
            }
            System.out.println();
        }
    }
    /**
     * 
     * @param board - the list of numbers
     * @param number - the number it needs to check
     * @param row -where in the rows its located
     * @return - returns true if theres a number returns false if there isnt
     */
    private static boolean isNumberInRow(int[][] board, int number, int row){
        for (int i = 0; i < GRID_SIZE; i++){
            if(board[row][i] == number){
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param board - the list of numners
     * @param number - the number it needs to check
     * @param row - Where the number is located on the row
     * @return - returns true if theres a number and returns false if there isnt
     */
    private static boolean isNumberInColumn(int[][] board, int number, int column){
        for (int i = 0; i < GRID_SIZE; i++){
            if(board[i][column] == number){
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param board - the list of numbers
     * @param number - the number it needs to check
     * @param row - Where the number is located on the row
     * @param column - Where the number is located on the column
     * @return -- returns true if there is a number in the box returns false if there isnt
     */
    private static boolean isNumberInBox(int[][] board, int number, int row, int column){
        // using mod to get to row/column of the position
        int localBoxRow = row - row % 3;  
        int localBoxColumn = column - column % 3;

        //goes through all 3 rows
        for(int i = localBoxRow; i < localBoxRow + 3; i++){
            //goes through all 3 columns
            for(int j = localBoxColumn; j < localBoxColumn + 3; j++){
                if(board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 
     * @param board - the list of numbers
     * @param number - the number it needs to check
     * @param row - Where the number is located in the row
     * @param column - Where the number is loated in the column
     * @return - returns if the number is able to be placed there
     */
    private static boolean isValidPlacement(int[][] board, int number, int row, int column){
        return !isNumberInRow(board, number, row) &&
            !isNumberInColumn(board, number, column) &&
            !isNumberInBox(board, number, row, column);
    }
/**
 * 
 * @param board - sodoku board with all the numbers
 * @return - returns false if it cant replace the number and returns true if the number works
 */
    private static boolean solveBoard(int [][] board){
        for(int row = 0; row < GRID_SIZE; row++){
            for (int column = 0; column < GRID_SIZE; column++){
                if(board[row][column] == 0){
                    for(int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++){
                        if(isValidPlacement(board, numberToTry, row, column)){
                            board[row][column] = numberToTry;

                            if (solveBoard(board)){
                                return true;
                            }else{
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


}

