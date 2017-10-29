import java.util.Scanner;

public class User extends Player {

    User(int size){
        super(size);
    }

    @Override
    public void newMove() {
        Scanner scanner = new Scanner(System.in);
        Integer row = 0;
        Integer col = 0;
        String rowInput = "";
        String colInput = "";

        do {
            // check for valid input in range
            System.out.println("Please enter row and column number separated by space: ");
            rowInput = scanner.next();
            colInput = scanner.next();
            row = DriverMain.checkRange(DriverMain.getInt(), 0, board.getSize() - 1);
            col = DriverMain.checkRange(DriverMain.getInt(), 0, board.getSize() - 1);

            // check for valid move on board
        }
        while (row == null || col == null || board.isValidMove(row, col));

        // make new move
        board.addMove(row, col);

        // check if the move turns any other move in the board and turn them
        board.flipColor();

    }
}
