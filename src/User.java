import java.util.Scanner;

public class User extends Player {

    User(int size){
        super(size);
    }

    @Override
    public void newMove() {
        Integer row = 0;
        Integer col = 0;

        do {
            // check for valid input in range
            System.out.println("Please enter row and column number separated by Enter (row and column start with 0): ");
            row = DriverMain.checkRange(DriverMain.getInt(), 0, board.getSize() - 1);
            col = DriverMain.checkRange(DriverMain.getInt(), 0, board.getSize() - 1);

            // check for valid move on board
        }
        while (row == null || col == null || !board.isValidMove(row, col));

        System.out.println("You move at " + row + ", "+ col);

        // make new move
        board.addMove(row, col);

        // add the move to the tree according to the color
        if (color == 'B'){
            // use the blackTree
            blackMoves.add(new Point(row, col));
        }
        else{
            whiteMoves.add(new Point(row, col));
        }

        // check if the move turns any other move in the board and turn them
        board.flipColor(row, col);

    }
}
