import java.util.ArrayList;
import java.util.TreeSet;

abstract public class Player {

    protected class Board {
        private char[][] grid;

        Board(Integer size){
            if (size == null || size < 2 || size%2 != 0){
                size = 4;
            }
            grid = new char [size][size];

            for (int ix = 0; ix < size; ix++){
                for(int iy = 0; iy < size; iy++){
                    grid[ix][iy] = '_';
                }
            }
            // make the 4 middle discs initialized
            grid[(size/2 - 1)][(size/2 - 1)] = grid[size/2][size/2] = BoardState.WHITE;
            grid[(size/2 - 1)][size/2] = grid[size/2][(size/2 - 1)] = BoardState.BLACK;

            // add the moves to the tree sets
            blackMoves.add(new Point(size/2 - 1, size/2 ));
            blackMoves.add(new Point(size/2, size/2 - 1));
            whiteMoves.add(new Point(size/2 - 1, size/2 - 1));
            whiteMoves.add(new Point(size/2, size/2 ));
        }

        /**
         * return the grid size
         * @return the length of the grid array
         */
        public int getSize(){
            return grid.length;
        }

        /*
        display the grid for user
         */
        public void displayBoard(){
            System.out.println("**************************");
            System.out.println();

            for (int ix = 0; ix < grid.length; ix++){
                for(int iy = 0; iy < grid.length; iy++){
                    System.out.print(grid[ix][iy]);
                }
                System.out.println();
            }
        }

        public boolean isValidMove(int row, int col) {
           /*
           loop through the list of opponent's list
           if the move is next to an opponent move
           not adjacent to any same color
           valid move
            */

            char up = BoardState.BLANK;
            char down = BoardState.BLANK;
            char left = BoardState.BLANK;
            char right = BoardState.BLANK;

            // if the spot is not blank, false
            if (grid[row][col] != BoardState.BLANK) {
                return false;
            }
            else {
                // check if the position of the spot is on edges or in centers
                if (haveUp(row)) {
                    up = grid[row - 1][col];
                }

                if (haveDown(row)) {
                    down = grid[row + 1][col];
                }

                if (haveLeft(col)) {
                    left = grid[row][col - 1];
                }

                if (haveRight(col)) {
                    right = grid[row][col + 1];
                }

                // check if the all the direction blank
                if (up == BoardState.BLANK && down == BoardState.BLANK && left == BoardState.BLANK && right == BoardState.BLANK) {
                    return false;
                }
                // if any is same color
                else if (up == color || down == color || left == color || right == color){
                    return false;
                }
                else {
                    return true;
                }
            }
        }

        private boolean haveUp(int curRow){
            return curRow >= 1;
        }

        private boolean haveDown(int curRow){
            return (curRow + 1) < getSize();
        }

        private boolean haveLeft(int curCol){
            return (curCol >= 1);
        }

        private boolean haveRight(int curCol){
            return (curCol + 1) < getSize();
        }

        public void addMoveToGrid(Point point){
            grid[point.getX()][point.getY()] = color;
        }

        // check if the last move can turn any other move on the grid
        private ArrayList<Point> getFlippedPoints(int curRow, int curCol){
            ArrayList<Point> flippedPointsList = new ArrayList<>();
            ArrayList<Point> candidatePoints = new ArrayList<>();

            final int DIRECTION = 4;
            int iRow = 0;
            int iCol = 0;
            char ch;

            int [] rowOffset = {0, 0, -1, 1};    // left, right, up, down
            int [] colOffset = {-1, 1, 0, 0};    // left, right, up, down
            /*
            if the slot is empty, then empty the list and break;
            slot is not the same color, then add to the list.
            slot is same color then return the list
            */
            for (int i = 0; i < DIRECTION; i++){
                iRow = curRow + rowOffset[i];
                iCol = curCol + colOffset[i];

                // move in one direction
                while (iRow >= 0 && iRow < getSize() && iCol >= 0 && iCol < getSize()){
                    // check if the slot is valid
                    ch = grid[iRow][iCol];

                    if (ch == BoardState.BLANK){
                        candidatePoints = new ArrayList<>();
                        break;
                    }
                    else if (ch != color){
                        candidatePoints.add(new Point(iRow, iCol));
                    }
                    else{
                        break;
                    }
                    // move to the next slot
                    iRow += rowOffset[i];
                    iCol += colOffset[i];
                }

                // add the points to the return list
                if (candidatePoints.size() != 0) {
                    flippedPointsList.addAll(candidatePoints);
                    candidatePoints = new ArrayList<>();
                }
            }

            return flippedPointsList;
        }



        // flip the possible discs
        protected void flipColor(int curRow, int curCol){
            ArrayList<Point> flippedPoints = getFlippedPoints(curRow, curCol);

            if (flippedPoints.size() != 0){
                // change the color in the board
                for (Point point : flippedPoints){
                    grid[point.getX()][point.getY()] = color;
                    // change in the list
                    addMoveToLists(point);
                }
            }
        }


        // check if the opponent can have another move?
        public boolean isEndGame(){
            /*
            have to check for the opposite color
            for each value in the list, check if the slot adjacent to it is a valid one
            if there is one, then return true
             */
            for (Point point: (color == BoardState.BLACK ? whiteMoves : blackMoves)){
                if (getNewNode(point) != null){
                    return true;
                }
            }
            return false;
        }




        protected Point getNewNode(Point curPoint){
            int row = curPoint.getX();
            int col = curPoint.getY();

            boolean up = haveUp(row);
            boolean down = haveDown(row);
            boolean left = haveLeft(col);
            boolean right = haveRight(col);

            if (up && isValidMove(row - 1, col)) {
                return new Point(row - 1, col);
            }

            if (down && isValidMove(row + 1, col)) {
                return new Point(row + 1, col);
            }

            if (left && isValidMove(row, col - 1)) {
                return new Point(row, col - 1);
            }

            if (right && isValidMove(row, col + 1)) {
                return new Point(row, col + 1);
            }
            return null;
        }

    }


    protected char color;
    protected static Board board;
    private static int ID = 0;
    protected static TreeSet<Point> blackMoves = new TreeSet<>();
    protected static TreeSet<Point> whiteMoves = new TreeSet<>();


    /**
     * initialize the player, the first one is B and the second one is W
     * @param size size of the grid
     */
    Player(int size){

        color = (ID == 0) ? BoardState.BLACK : BoardState.WHITE;

        if (ID == 0){
            board = new Board(size);
        }
        ID++;
    }

    public char getColor() {
        return color;
    }


    public void setColor(char color) {
        this.color = color;
    }

    /**
     * abstract method to make a new move in the game
     */
    public abstract void newMove();

    protected void addMoveToLists(Point point){
        if (color == BoardState.BLACK){
            blackMoves.add(point);
            whiteMoves.remove(point);
        }
        else{
            whiteMoves.add(point);
            blackMoves.remove(point);
        }
    }

    public int getBlackMoves(){
        return blackMoves.size();
    }

    public int getWhiteMoves(){
        return whiteMoves.size();
    }

}
