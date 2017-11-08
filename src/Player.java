import java.util.ArrayList;
import java.util.TreeSet;

abstract public class Player {

    protected class Board {
        private char[][] grid;

        Board(){

        }


        protected void createBoard(Integer size){
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
            grid[(size/2 - 1)][(size/2 - 1)] = grid[size/2][size/2] = 'W';
            grid[(size/2 - 1)][size/2] = grid[size/2][(size/2 - 1)] = 'B';

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

            char blank = '_';
            char up = blank;
            char down = blank;
            char left = blank;
            char right = blank;

            // if the spot is not blank, false
            if (grid[row][col] != blank) {
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
                if (up == blank && down == blank && left == blank && right == blank) {
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

        /**
         * add a new valid move to the grid
         * @param row row number
         * @param col column number
         */
        public void addMove(int row, int col){
            grid[row][col] = color;
            // add the move to the list
        }

        // check if the last move can turn any other move on the grid
        private ArrayList<Point> getFlippedPoints(int curRow, int curCol){
            ArrayList<Point> flippedPointsList = new ArrayList<Point>();
            ArrayList<Point> candidatePoints = new ArrayList<Point>();
            int iRow = 0;
            int iCol = 0;
            char blankDisc = '_';


            // check for the left direction
            for (iRow = curRow; iRow >= 0; iRow--){
                /*
                if the slot is empty, then empty the list and break;
                slot is not the same color, then add to the list.
                slot is same color then return the list
                 */
                if (grid[iRow][iCol] == blankDisc){
                    candidatePoints = new ArrayList<>();
                    break;
                }
                else if (grid[iRow][iCol] != color){
                    candidatePoints.add(new Point(iRow, iCol));
                }
                else{
                    break;
                }
            }

            if (candidatePoints.size() != 0){
                flippedPointsList.addAll(candidatePoints);
                candidatePoints = new ArrayList<>();
            }

            // check for the right direction
            for (iRow = curRow; iRow < getSize(); iRow++){
                /*
                if the slot is empty, then empty the list and break;
                slot is not the same color, then add to the list.
                slot is same color then return the list
                 */
                if (grid[iRow][iCol] == blankDisc){
                    candidatePoints = new ArrayList<>();
                    break;
                }
                else if (grid[iRow][iCol] != color){
                    candidatePoints.add(new Point(iRow, iCol));
                }
                else{
                    break;
                }
            }

            if (candidatePoints.size() != 0){
                flippedPointsList.addAll(candidatePoints);
                candidatePoints = new ArrayList<>();
            }

            // check for the up direction

            for (iRow = curRow; iRow >= 0; iRow--){
                /*
                if the slot is empty, then empty the list and break;
                slot is not the same color, then add to the list.
                slot is same color then return the list
                 */
                if (grid[iRow][iCol] == blankDisc){
                    candidatePoints = new ArrayList<>();
                    break;
                }
                else if (grid[iRow][iCol] != color){
                    candidatePoints.add(new Point(iRow, iCol));
                }
                else{
                    break;
                }
            }

            if (candidatePoints.size() != 0){
                flippedPointsList.addAll(candidatePoints);
                candidatePoints = new ArrayList<>();
            }
            // check for the down direction
            for (iRow = curRow; iRow < getSize(); iRow++){
                /*
                if the slot is empty, then empty the list and break;
                slot is not the same color, then add to the list.
                slot is same color then return the list
                 */
                if (grid[iRow][iCol] == blankDisc){
                    candidatePoints = new ArrayList<>();
                    break;
                }
                else if (grid[iRow][iCol] != color){
                    candidatePoints.add(new Point(iRow, iCol));
                }
                else{
                    break;
                }
            }

            if (candidatePoints.size() != 0){
                flippedPointsList.addAll(candidatePoints);
            }

            return flippedPointsList;
        }



        // flip the possible discs
        protected void flipColor(int curRow, int curCol){
            ArrayList<Point> flippedPoints = getFlippedPoints(curRow, curCol);

            if (flippedPoints.size() == 0){
                // change the color in the board
                for (Point point : flippedPoints){
                    grid[point.getX()][point.getY()] = color;
                    // change in the list
                    movePoints(point);
                }
            }
        }


        // check if can have another move?
        public boolean isEndGame(){
            /*
            for each value in the list, check if the slot adjacent to it is a valid one
            if there is one, then return true
             */
            for (Point point: (color == 'B' ? blackMoves : whiteMoves)){
                return haveNewNode(point.getX(), point.getY());
            }

            return false;
        }

        private boolean haveNewNode(int row, int col){
            /*
            for this node, check if up, down,... exist
            then check if each direction is a valid move
            if yes, then return true
             */

            if (haveUp(row) && isValidMove(row - 1, col)) {
                return true;
            }

            if (haveDown(row) && isValidMove(row + 1, col)) {
                return true;
            }

            if (haveLeft(col) && isValidMove(row, col - 1)) {
                return true;
            }

            if (haveRight(col) && isValidMove(row, col + 1)) {
                return true;
            }

            return false;
        }

        protected Point getNewNode(Point curPoint){
            Point newNode = null;
            int row = curPoint.getX();
            int col = curPoint.getY();

            if (haveUp(row) && isValidMove(row - 1, col)) {
                newNode = new Point(row - 1, col);
            }

            if (haveDown(row) && isValidMove(row + 1, col)) {
                newNode = new Point(row + 1, col);
            }

            if (haveLeft(col) && isValidMove(row, col - 1)) {
                newNode = new Point(row, col - 1);
            }

            if (haveRight(col) && isValidMove(row, col + 1)) {
                newNode = new Point(row, col + 1);
            }

            return newNode;
        }

        public int getNumberOfDisc(){
            if (color == 'B'){
                return blackMoves.size();
            }
            else{
                return whiteMoves.size();
            }
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

        color = (ID == 0) ? 'B' : 'W';
        board.createBoard(size);
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

    protected void movePoints(Point point){
        if (color == 'B'){
            blackMoves.add(point);
            whiteMoves.remove(point);
        }
        else{
            whiteMoves.add(point);
            blackMoves.remove(point);
        }
    }
}
