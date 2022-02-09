package tictactoe;

import java.util.Random;

public class ComputerPlayerEasy extends ComputerPlayer{

    final static String MOVE_GAME_LEVEL_EASY_STR = "Making move level \"easy\"";
    final static char SPACE_CHAR = ' ';
    static Random rand = new Random(System.currentTimeMillis());

    public ComputerPlayerEasy() {

    }

    public ComputerPlayerEasy (char x_o) {
        super.x_o = x_o;
    }

    @Override
    public char[][] turn(char[][] gameGrid) {
        System.out.println("Computer Player Easy Turn " + x_o);
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

        System.out.println(MOVE_GAME_LEVEL_EASY_STR);
        return gameGrid;
    }
}
