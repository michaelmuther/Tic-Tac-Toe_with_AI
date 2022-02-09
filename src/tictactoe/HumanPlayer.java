package tictactoe;

import java.util.Scanner;

public class HumanPlayer extends Player {

    static Scanner scanner = new Scanner(System.in);

    public HumanPlayer() {

    }

    public HumanPlayer (char x_o) {
        super.x_o = x_o;
    }

    @Override
    public char[][] turn(char[][] gameGrid) {
        final String INPUT_ENTER_COORDINATES_STR = "Enter the coordinates:";
        final String ERROR_OCCUPIED_CELL_STR = "This cell is occupied! Choose another one!";
        final String ERROR_INVALID_ENTRY_STR = "You should enter numbers!";
        final String ERROR_INPUT_OUT_OF_RANGE_STR = "Coordinates should be from 1 to 3!";

        System.out.println("Human turn " + x_o); // XXX TEMP XXX
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
                    gameGrid[cellIndices[0]][cellIndices[1]] = x_o;
                }
            } else {
                System.out.println(ERROR_INPUT_OUT_OF_RANGE_STR);
            }
        } while (!validCoordinateInput);
        return gameGrid;
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


