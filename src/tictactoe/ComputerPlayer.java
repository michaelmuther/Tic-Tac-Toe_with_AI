package tictactoe;

public abstract class ComputerPlayer extends Player  {

    public ComputerPlayer() {

    }

    public ComputerPlayer (char x_o) {
        super.x_o = x_o;
    }

    @Override
    public abstract char[][] turn(char[][] gameGrid);

}
