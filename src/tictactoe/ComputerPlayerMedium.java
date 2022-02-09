package tictactoe;

import java.util.Random;

public class ComputerPlayerMedium extends ComputerPlayer{

    final static String MOVE_GAME_LEVEL_MEDIUM_STR = "Making move level \"medium\"";
    final char X_CHAR = 'X';
    final char O_CHAR = 'O';
    final static char SPACE_CHAR = ' ';
    static Random rand = new Random(System.currentTimeMillis());
    private boolean turnOver = false;
    char[][] tempGrid = new char[3][3];


    public ComputerPlayerMedium() {

    }

    public ComputerPlayerMedium (char x_o) {
        super.x_o = x_o;
    }

    @Override
    public char[][] turn(char[][] gameGrid) {
        if (winTurn(gameGrid)) {
            System.out.println(MOVE_GAME_LEVEL_MEDIUM_STR);
            return gameGrid;
        } else if (blockTurn(gameGrid)) {
            System.out.println(MOVE_GAME_LEVEL_MEDIUM_STR);
            return gameGrid;
        } else {
            randomTurn(gameGrid);
            System.out.println(MOVE_GAME_LEVEL_MEDIUM_STR);
            return gameGrid;
        }
    }

    private boolean winTurn(char[][] gameGrid) {
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                copyGameGrid(gameGrid);
                boolean win = false;
                if (tempGrid[i][j] == ' ') {
                    tempGrid[i][j] = x_o;
                } else {
                    continue;
                }
                if (tempGrid[0][0] == x_o && tempGrid[0][1] == x_o && tempGrid[0][2] == x_o) { //row 1 horizontal
                    win = true;
                } else if (tempGrid[1][0] == x_o && tempGrid[1][1] == x_o && tempGrid[1][2] == x_o) { // row 2 horizontal
                    win = true;
                } else if (tempGrid[2][0] == x_o && tempGrid[2][1] == x_o && tempGrid[2][2] == x_o) { // row 3 horizontal
                    win = true;
                } else if (tempGrid[0][0] == x_o && tempGrid[1][0] == x_o && tempGrid[2][0] == x_o) { // column 1 vertical
                    win = true;
                } else if (tempGrid[0][1] == x_o && tempGrid[1][1] == x_o && tempGrid[2][1] == x_o) { // column 2 vertical
                    win = true;
                } else if (tempGrid[0][2] == x_o && tempGrid[1][2] == x_o && tempGrid[2][2] == x_o) { // column3 vertical
                    win = true;
                } else if (tempGrid[0][0] == x_o && tempGrid[1][1] == x_o && tempGrid[2][2] == x_o) { // backslash diagonal
                    win = true;
                } else if (tempGrid[0][2] == x_o && tempGrid[1][1] == x_o && tempGrid[2][0] == x_o) { // forwardslash diagonal
                    win = true;
                }               
                if (win) {
                    gameGrid[i][j] = x_o;
                    turnOver = true;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean blockTurn(char[][] gameGrid) {
        char opponent = x_o == X_CHAR ? O_CHAR : X_CHAR;
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                copyGameGrid(gameGrid);
                boolean win = false;
                if (tempGrid[i][j] == ' ') {
                    tempGrid[i][j] = opponent;
                } else {
                    continue;
                }
                if (tempGrid[0][0] == opponent && tempGrid[0][1] == opponent && tempGrid[0][2] == opponent) { //row 1 horizontal
                    win = true;
                } else if (tempGrid[1][0] == opponent && tempGrid[1][1] == opponent && tempGrid[1][2] == opponent) { // row 2 horizontal
                    win = true;
                } else if (tempGrid[2][0] == opponent && tempGrid[2][1] == opponent && tempGrid[2][2] == opponent) { // row 3 horizontal
                    win = true;
                } else if (tempGrid[0][0] == opponent && tempGrid[1][0] == opponent && tempGrid[2][0] == opponent) { // column 1 vertical
                    win = true;
                } else if (tempGrid[0][1] == opponent && tempGrid[1][1] == opponent && tempGrid[2][1] == opponent) { // column 2 vertical
                    win = true;
                } else if (tempGrid[0][2] == opponent && tempGrid[1][2] == opponent && tempGrid[2][2] == opponent) { // column3 vertical
                    win = true;
                } else if (tempGrid[0][0] == opponent && tempGrid[1][1] == opponent && tempGrid[2][2] == opponent) { // backslash diagonal
                    win = true;
                } else if (tempGrid[0][2] == opponent && tempGrid[1][1] == opponent && tempGrid[2][0] == opponent) { // forwardslash diagonal
                    win = true;
                }
                if (win) {
                    gameGrid[i][j] = x_o;
                    turnOver = true;
                    return true;
                }
            }
        }
        return false;
    }

    private char[][] randomTurn(char[][] gameGrid) {
        boolean validMove = false;
        int randRow;
        int randCol;
        do {
            randRow = rand.nextInt(3);
            randCol = rand.nextInt(3);
            if (gameGrid[randRow][randCol] == SPACE_CHAR) {
                gameGrid[randRow][randCol] = x_o;
                validMove = true;
            }
        } while (!validMove);
        return gameGrid;
    }

    private void copyGameGrid (char[][] gameGrid) {
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                tempGrid[i][j] = gameGrid[i][j];
            }
        }
    }
}
