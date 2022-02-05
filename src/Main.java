import java.util.Scanner;
import java.util.Random;

public class Main {

    final static String INPUT_ENTER_COORDINATES_STR = "Enter the coordinates:";
    final static String ERROR_OCCUPIED_CELL_STR = "This cell is occupied! Choose another one!";
    final static String ERROR_INVALID_ENTRY_STR = "You should enter numbers!";
    final static String ERROR_INPUT_OUT_OF_RANGE_STR = "Coordinates should be from 1 to 3!";
    final static String STATE_DRAW_STR = "Draw";
    final static String STATE_WIN_STR = " wins";
    final static String STATE_GAME_NOT_FINISHED_STR = "Game not finished";
    final static String MOVE_GAME_LEVEL_EASY_STR = "Making move level \"easy\"";
    final static String GAME_GRID_BORDER_STR = "---------";
    final static String GAME_GRID_ROW_STR = "| %C %C %C |\n";
    final static char X_CHAR = 'X';
    final static char O_CHAR = 'O';
    final static char SPACE_CHAR = ' ';
    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random(System.currentTimeMillis());
    static boolean gameOver = false;
    static char[][] gameGrid = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    static char turn = X_CHAR;

    public static void printGameGrid(char[][] gameGrid) {
        System.out.println(GAME_GRID_BORDER_STR);
        System.out.printf(GAME_GRID_ROW_STR, gameGrid[0][0], gameGrid[0][1], gameGrid[0][2]);
        System.out.printf(GAME_GRID_ROW_STR, gameGrid[1][0], gameGrid[1][1], gameGrid[1][2]);
        System.out.printf(GAME_GRID_ROW_STR, gameGrid[2][0], gameGrid[2][1], gameGrid[2][2]);
        System.out.println(GAME_GRID_BORDER_STR);
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkCellInput(int[] cellIndices, char[][] gameGrid) {
        int index1 = cellIndices[0];
        int index2 = cellIndices[1];
        return gameGrid[index1][index2] == X_CHAR || gameGrid[index1][index2] == O_CHAR;
    }

    public static boolean checkGameOver(char[][] gameGrid, char xo) {
        String winMessage = xo + STATE_WIN_STR;
        int turns = 0;
        boolean win = false;
        if (gameGrid[0][0] == xo && gameGrid[0][1] == xo && gameGrid[0][2] == xo) { //row 1 horizontal
            win = true;
        } else if (gameGrid[1][0] == xo && gameGrid[1][1] == xo && gameGrid[1][2] == xo) { // row 2 horizontal
            win = true;
        } else if (gameGrid[2][0] == xo && gameGrid[2][1] == xo && gameGrid[2][2] == xo) { // row 3 horizontal
            win = true;
        } else if (gameGrid[0][0] == xo && gameGrid[1][0] == xo && gameGrid[2][0] == xo) { // column 1 vertical
            win = true;
        } else if (gameGrid[0][1] == xo && gameGrid[1][1] == xo && gameGrid[2][1] == xo) { // column 2 vertical
            win = true;
        } else if (gameGrid[0][2] == xo && gameGrid[1][2] == xo && gameGrid[2][2] == xo) { // column3 vertical
            win = true;
        } else if (gameGrid[0][0] == xo && gameGrid[1][1] == xo && gameGrid[2][2] == xo) { // backslash diagonal
            win = true;
        } else if (gameGrid[0][2] == xo && gameGrid[1][1] == xo && gameGrid[2][0] == xo) { // forwardslash diagonal
            win = true;
        }
        if (win) {
            System.out.println(winMessage);
            return win;
        }
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                if (gameGrid[i][j] != ' ') {
                    turns++;
                }
            }
        }
        if (turns == 9) {
            System.out.println(STATE_DRAW_STR);
            return true;
        }
        return win;
    }

    public static int countXo(char[][] gameGrid, char xo) {
        int count = 0;
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                if (gameGrid[i][j] == xo) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void userTurn() {
        boolean validCoordinateInput = false;
        int one = 1; // difference between coordinate and index
        int[] cellIndices = new int[2];
        do {
            boolean inputFormatValid = false;
            do { // check if input is two numbers
                System.out.print(INPUT_ENTER_COORDINATES_STR);
                inputFormatValid = false;
                String input1String = scanner.next();
                if (isNumeric(input1String)) {
                    cellIndices[0] = Integer.parseInt(input1String) - one;
                    String input2String = scanner.next();
                    if (isNumeric(input2String)) {
                        cellIndices[1] = Integer.parseInt(input2String) - one;
                        inputFormatValid = true;
                    } else {
                        System.out.println(ERROR_INVALID_ENTRY_STR);
                    }
                } else {
                    System.out.println(ERROR_INVALID_ENTRY_STR);
                }
            } while (!inputFormatValid);

            if (cellIndices[0] >= 0 && cellIndices[0] <= 2 && cellIndices[1] >= 0 && cellIndices[1] <= 2) {
                if (checkCellInput(cellIndices, gameGrid)) {
                    System.out.println(ERROR_OCCUPIED_CELL_STR);
                } else {
                    validCoordinateInput = true;
                    gameGrid[cellIndices[0]][cellIndices[1]] = X_CHAR;
                }
            } else {
                System.out.println(ERROR_INPUT_OUT_OF_RANGE_STR);
            }
        } while (!validCoordinateInput);
        printGameGrid(gameGrid);
        gameOver = checkGameOver(gameGrid, turn);
        turn = O_CHAR;
    }

    public static void computerTurn() {
        boolean validMove = false;
        int randRow;
        int randCol;
        do {
            randRow = rand.nextInt(3);
            randCol = rand.nextInt(3);
            if (gameGrid[randRow][randCol] == SPACE_CHAR) {
                gameGrid[randRow][randCol] = O_CHAR;
                validMove = true;
            }
        } while (!validMove);

        System.out.println(MOVE_GAME_LEVEL_EASY_STR);
        printGameGrid(gameGrid);
        gameOver = checkGameOver(gameGrid, turn);
        turn = X_CHAR;
    }

    public static void playGame() {
        printGameGrid(gameGrid);
        do {
            if (turn == X_CHAR) {
                userTurn();
            } else {
                computerTurn();
            }
        } while (!gameOver);
    }

    public static void main(String[] args) {
        playGame();
    }

}

//        for (int i = 0; i < gameGrid.length; i++) {
//            for (int j = 0; j < gameGrid[i].length; j++) {
//                if (gameGrid[i][j] != ' ') {
//                    turns++;
//                }
//            }
//        }
//        if (turns == 9) {
//            System.out.println(STATE_DRAW_STR);
//            return true;
//        }

//    final static String STATE_X_WINS_STR = "X wins";
//    final static String STATE_O_WINS_STR = "O wins";

//    public static char[][] stringToTwoDimensionalArray(String input) {
//        char[][] temp = new char[3][3];
//        int arrayRows = 3;
//        int arrayColumns = 3;
//        int inputIndex = 0;
//        for (int i = 0; i < arrayRows; i++) {
//            for (int j = 0; j < arrayColumns; j++) {
//                if (input.charAt(inputIndex) != '_') {
//                    temp[i][j] = input.charAt(inputIndex);
//                } else {
//                    temp[i][j] = ' ';
//                }
//                inputIndex++;
//            }
//        }
//        return temp;
//    }

//    public static void gameInputString() {
//        System.out.println(INPUT_ENTER_CELLS_STR);
//        input = scanner.nextLine();
//    }



//    public static char determineFirstTurn(char[][] gameGrid) {
//        int xCount = countXo(gameGrid, X_CHAR);
//        int oCount = countXo(gameGrid, O_CHAR);
//        return xCount > oCount ? O_CHAR : X_CHAR;
//    }

//        gameGrid = stringToTwoDimensionalArray(input);
//        turn = determineFirstTurn(gameGrid);
//    gameInputString();
//    static char[][] gameGrid = {new char[3][3]};

//    final static String INPUT_ENTER_CELLS_STR = "Enter the cells:";

//    static String input = "";