package tictactoe;

public class ComputerPlayerHard extends ComputerPlayer{

    public ComputerPlayerHard() {

    }

    public ComputerPlayerHard (char x_o) {
        super.x_o = x_o;
    }

    @Override
    public char[][] turn(char[][] gameGrid) {
        System.out.println("Computer Player Hard Turn " + x_o);

        return gameGrid;
    }
}
