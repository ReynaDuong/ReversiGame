import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class Computer extends Player {

    Computer(int size){
        super(size);
    }

    /*
    make a new move
     */
    @Override
    public void newMove(){

        final char BLACK = 'B';

        /*
        search the list of move of the opponent to find a new move
        validate the move
         */

        Point newPoint = null;
        for (Point point: (color == 'B' ? whiteMoves : blackMoves)){
            if (board.getNewNode(point) != null){
                newPoint = point;
                break;
            }
        }
        // add the move to the board
        board.addMove(newPoint.getX(), newPoint.getY());

        // add move to the list
        if (color == BLACK){
            blackMoves.add(newPoint);
        }
        else{
            whiteMoves.add(newPoint);
        }

        System.out.println("Computer moves at " + newPoint.getX() + ", " + newPoint.getY());
    }

}
