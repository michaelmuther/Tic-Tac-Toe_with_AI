package tictactoe;

public abstract class Player {

    protected char x_o;
    final char X_CHAR = 'X';
    final char O_CHAR = 'O';

    protected boolean checkCellInput(int[] cellIndices, char[][] gameGrid) {
        int index1 = cellIndices[0];
        int index2 = cellIndices[1];
        return gameGrid[index1][index2] == X_CHAR || gameGrid[index1][index2] == O_CHAR;
    }

    public Player() {

    }

    public Player (char x_o) { // you could pass the object into the constructor also.  Try this in the future
        this.x_o = x_o;
    }

    public abstract char[][] turn(char[][] gameGrid);

}



