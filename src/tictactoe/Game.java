package tictactoe;

import java.util.Scanner;

public class Game {

    //Instance variables:
    final String INPUT_PARAM_USER = "user";
    final String INPUT_PARAM_EASY = "easy";
    final String INPUT_PARAM_MED = "medium";
    final String INPUT_PARAM_HARD = "hard";
    final char X_CHAR = 'X';
    final char O_CHAR = 'O';
    final char SPACE_CHAR = ' ';
    public char[][] gameGrid = { // instance variable
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    final char[][] emptyGameGrid = { // instance variable
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    private boolean beginGame = true; // instance variable
    private char currentPlayerChar = X_CHAR;
    private String playerOneInputParameter;
    private String playerTwoInputParameter;
    private Player playerX;
    private Player playerY;

    private void printGameGrid() { // this works
        final String GAME_GRID_BORDER_STR = "---------";
        final String GAME_GRID_ROW_STR = "| %C %C %C |\n";
        System.out.println(GAME_GRID_BORDER_STR);
        System.out.printf(GAME_GRID_ROW_STR, gameGrid[0][0], gameGrid[0][1], gameGrid[0][2]);
        System.out.printf(GAME_GRID_ROW_STR, gameGrid[1][0], gameGrid[1][1], gameGrid[1][2]);
        System.out.printf(GAME_GRID_ROW_STR, gameGrid[2][0], gameGrid[2][1], gameGrid[2][2]);
        System.out.println(GAME_GRID_BORDER_STR);
    }

    private boolean checkGameOver() { // untested
        final String STATE_DRAW_STR = "Draw";
        final String STATE_WIN_STR = " wins";
        String winMessage = currentPlayerChar + STATE_WIN_STR;
        int turns = 0;
        boolean win = false;
        if (gameGrid[0][0] == currentPlayerChar && gameGrid[0][1] == currentPlayerChar && gameGrid[0][2] == currentPlayerChar) { //row 1 horizontal
            win = true;
        } else if (gameGrid[1][0] == currentPlayerChar && gameGrid[1][1] == currentPlayerChar && gameGrid[1][2] == currentPlayerChar) { // row 2 horizontal
            win = true;
        } else if (gameGrid[2][0] == currentPlayerChar && gameGrid[2][1] == currentPlayerChar && gameGrid[2][2] == currentPlayerChar) { // row 3 horizontal
            win = true;
        } else if (gameGrid[0][0] == currentPlayerChar && gameGrid[1][0] == currentPlayerChar && gameGrid[2][0] == currentPlayerChar) { // column 1 vertical
            win = true;
        } else if (gameGrid[0][1] == currentPlayerChar && gameGrid[1][1] == currentPlayerChar && gameGrid[2][1] == currentPlayerChar) { // column 2 vertical
            win = true;
        } else if (gameGrid[0][2] == currentPlayerChar && gameGrid[1][2] == currentPlayerChar && gameGrid[2][2] == currentPlayerChar) { // column3 vertical
            win = true;
        } else if (gameGrid[0][0] == currentPlayerChar && gameGrid[1][1] == currentPlayerChar && gameGrid[2][2] == currentPlayerChar) { // backslash diagonal
            win = true;
        } else if (gameGrid[0][2] == currentPlayerChar && gameGrid[1][1] == currentPlayerChar && gameGrid[2][0] == currentPlayerChar) { // forwardslash diagonal
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

    // needs to start game: print game menu
    public void startGame() {
        do {
            gameGrid = emptyGameGrid.clone();
            gameMenu();
            if (beginGame) {
                gameSetup();
                gameControl();
            }
        } while (beginGame);
    }

    private void gameSetup() { // this works
         switch (playerOneInputParameter) {
             case INPUT_PARAM_USER:
                 playerX = new HumanPlayer(X_CHAR);
                 break;
             case INPUT_PARAM_EASY:
                 playerX = new ComputerPlayerEasy(X_CHAR);
                 break;
             case INPUT_PARAM_MED:
                 playerX = new ComputerPlayerMedium(X_CHAR);
                 break;
             case INPUT_PARAM_HARD:
                 playerX = new ComputerPlayerHard(X_CHAR);
                 break;
         }

        switch (playerTwoInputParameter) {
            case INPUT_PARAM_USER:
                playerY = new HumanPlayer(O_CHAR);
                break;
            case INPUT_PARAM_EASY:
                playerY = new ComputerPlayerEasy(O_CHAR);
                break;
            case INPUT_PARAM_MED:
                playerY = new ComputerPlayerMedium(O_CHAR);
                break;
            case INPUT_PARAM_HARD:
                playerY = new ComputerPlayerHard(O_CHAR);
                break;
        }
    }

    private void gameControl() {
        resetGameGrid();
        boolean gameOver;
        printGameGrid();
        boolean xTurn = true;
        do {
            if (xTurn) {
                currentPlayerChar = X_CHAR;
                gameGrid = playerX.turn(gameGrid);
            } else {
                currentPlayerChar = O_CHAR;
                gameGrid = playerY.turn(gameGrid);
            }
            printGameGrid();
            gameOver = checkGameOver();
            xTurn = !xTurn; // flips turn over
        } while (!gameOver);
    }

    private void gameMenu() { //This works

        final String INPUT_ENTER_GAME_PARAMS_STR = "Input command: ";
        final String INPUT_PARAM_EXIT = "exit";
        final String INPUT_SPACE_STR = " ";

        Scanner scanner = new Scanner(System.in);
        String input;
        String[] inputArray;
        boolean validInput;

        do {
            System.out.println(INPUT_ENTER_GAME_PARAMS_STR);
            input = scanner.nextLine();
            if (input.equals(INPUT_PARAM_EXIT)) {
                validInput = true;
                beginGame = false;
            } else {
                inputArray = input.split(INPUT_SPACE_STR);
                validInput = gameMenuInputCheck(inputArray);
            }
        } while (!validInput);
    }

    private boolean gameMenuInputCheck(String[] inputArray) { //This works
        final String INPUT_PARAM_START = "start";
        final String ERROR_BAD_PARAMS_STR = "Bad Parameters!";

        if (inputArray.length != 3) {
            System.out.println(ERROR_BAD_PARAMS_STR);
            return false;
        }
        boolean input1valid = inputArray[0].equals(INPUT_PARAM_START);
        boolean input2valid = inputArray[1].equals(INPUT_PARAM_EASY) || inputArray[1].equals(INPUT_PARAM_MED) ||
                inputArray[1].equals(INPUT_PARAM_HARD) || inputArray[1].equals(INPUT_PARAM_USER);
        boolean input3valid = inputArray[2].equals(INPUT_PARAM_EASY) || inputArray[2].equals(INPUT_PARAM_MED) ||
                inputArray[2].equals(INPUT_PARAM_HARD) || inputArray[2].equals(INPUT_PARAM_USER);
        if (input1valid && input2valid && input3valid) {
            playerOneInputParameter = inputArray[1];
            playerTwoInputParameter = inputArray[2];
            return true;
        } else {
            System.out.println(ERROR_BAD_PARAMS_STR);
        }
        return false;
    }

    private void resetGameGrid() {
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                gameGrid[i][j] = SPACE_CHAR;
            }
        }
    }


}

//         switch (playerOneInputParameter) {
//                 case INPUT_PARAM_USER -> playerX = new HumanPlayer(X_CHAR);
//                 case INPUT_PARAM_EASY -> playerX = new ComputerPlayerEasy(X_CHAR);
//                 case INPUT_PARAM_MED -> playerX = new ComputerPlayerMedium(X_CHAR);
//                 case INPUT_PARAM_HARD -> playerX = new ComputerPlayerHard(X_CHAR);
//                 }
//                 switch (playerTwoInputParameter) {
//                 case INPUT_PARAM_USER -> playerY = new HumanPlayer(O_CHAR);
//                 case INPUT_PARAM_EASY -> playerY = new ComputerPlayerEasy(O_CHAR);
//                 case INPUT_PARAM_MED -> playerY = new ComputerPlayerMedium(O_CHAR);
//                 case INPUT_PARAM_HARD -> playerY = new ComputerPlayerHard(O_CHAR);
//                 }
