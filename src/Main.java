import java.util.Scanner;

public class Main {

    final static String INPUT_ENTER_CELLS_STR = "Enter the cells:";
    final static String INPUT_ENTER_COORDINATES_STR = "Enter the coordinates:";
    final static String ERROR_OCCUPIED_CELL_STR = "This cell is occupied! Choose another one!";
    final static String ERROR_INVALID_ENTRY_STR = "You should enter numbers!";
    final static String ERROR_INPUT_OUT_OF_RANGE_STR = "Coordinates should be from 1 to 3!";
    final static String STATE_DRAW_STR = "Draw";
    final static String STATE_X_WINS_STR = "X wins";
    final static String STATE_O_WINS_STR = "O wins";
    final static String STATE_WIN_STR = " wins";
    final static String STATE_GAME_NOT_FINISHED_STR = "Game not finished";
    final static String GAME_GRID_BORDER_STR = "---------";
    final static String GAME_GRID_ROW_STR = "| %C %C %C |\n";
    final static char X_CHAR = 'X';
    final static char O_CHAR = 'O';
    static Scanner scanner = new Scanner(System.in);

    static char[][] gameGrid = new char[3][3];

    static String input = "";

    static boolean xTurn = true;
    static char turn = 'X';

    public static char[][] stringToTwoDimensionalArray(String input) {
        char[][] temp = new char[3][3];
        int arrayRows = 3;
        int arrayColumns = 3;
        int inputIndex = 0;
        for (int i = 0; i < arrayRows; i++) {
            for (int j = 0; j < arrayColumns; j++) {
                if (input.charAt(inputIndex) != '_') {
                    temp[i][j] = input.charAt(inputIndex);
                } else {
                    temp[i][j] = ' ';
                }
                inputIndex++;
            }
        }
        return temp;
    }

    public static void printGameGrid(char[][] gameGrid) {
        System.out.println(GAME_GRID_BORDER_STR);
        System.out.printf(GAME_GRID_ROW_STR, gameGrid[0][0], gameGrid[0][1], gameGrid[0][2]);
        System.out.printf(GAME_GRID_ROW_STR, gameGrid[1][0], gameGrid[1][1], gameGrid[1][2]);
        System.out.printf(GAME_GRID_ROW_STR, gameGrid[2][0], gameGrid[2][1], gameGrid[2][2]);
        System.out.println(GAME_GRID_BORDER_STR);
    }

    public static void gameInputString() {
        System.out.println(INPUT_ENTER_CELLS_STR);
        input = scanner.nextLine();
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
        return gameGrid[index1][index2] == 'X' || gameGrid[index1][index2] == 'O';
    }

    public static boolean checkGameOver(char[][] gameGrid, char xo) {
        String winMessage = xo + STATE_WIN_STR;
        int turns = 0;
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
        } else {
            System.out.println(STATE_GAME_NOT_FINISHED_STR);
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

    public static void main(String[] args) {
        gameInputString();
        gameGrid = stringToTwoDimensionalArray(input);
        printGameGrid(gameGrid);
        boolean validCoordinateInput = false;
        String errorMessage = "";
        int one = 1; // difference between coordinate and index
        int[] cellIndices = new int[2];
        do {
            boolean inputFormatValid = false;
            int validInputs = 0;
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
                    gameGrid[cellIndices[0]][cellIndices[1]] = turn;
                    xTurn = !xTurn;
                }
            } else {
                System.out.println(ERROR_INPUT_OUT_OF_RANGE_STR);
            }
        } while (!validCoordinateInput);
        printGameGrid(gameGrid);
        checkGameOver(gameGrid,'X');
    }
}