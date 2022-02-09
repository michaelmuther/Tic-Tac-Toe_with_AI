package tictactoe;

public class Main {

    public static void main(String[] args) {
        Game test = new Game();
        test.startGame();
    }
}

// "A sympton of procedural code is functions with many parameters."
// This is written in a procedural programming style; this must be updated to OOP
// "Uncle Bob says, the best functions are those with no parameters."  Bob is Robert C. Martin
// The fewer parameters, the easier it is to use and maintain that function

//    public static int countXo(char[][] gameGrid, char xo) {
//        int count = 0;
//        for (int i = 0; i < gameGrid.length; i++) {
//            for (int j = 0; j < gameGrid[i].length; j++) {
//                if (gameGrid[i][j] == xo) {
//                    count++;
//                }
//            }
//        }
//        return count;
//    }



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

//    final static String STATE_GAME_NOT_FINISHED_STR = "Game not finished";