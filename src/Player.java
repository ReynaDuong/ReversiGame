abstract public class Player {

    protected class Board {
        private char[][] grid;

        /**
         * if the size is less than 2 or not even, then use the default size
         * create a new board and initialize with _
         * @param size the size of the grid
         */
        Board(int size){
            if (size < 2 || size%2 != 0){
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
        }

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

        public boolean isValidMove(int row, int col){
            // check if the adjacent slot is empty or is different color
            return (grid[row][col - 1] != color & grid[row][col + 1] != color & grid[row - 1][col] != color & grid[row + 1][col] != color);
        }

        /**
         * add a new valid move to the grid
         * @param row row number
         * @param col column number
         */
        public void addMove(int row, int col){
            grid[row][col] = color;
        }

        // check if the last move can turn any other move on the grid
        private int[] getFlippingPoints(int direction, int currentRow, int currentColumn){
            int [] startPoint = new int[2];
            int row = 0;
            int column = 0;

            for (int ix = row; ix >= 0; ix--){
                if (grid[row][column] == color){
                    startPoint[0] = row;
                    startPoint[1] = column;
                    break;
                }
            }


            return startPoint;
        }



        // turn the color
        protected void flipColor(){
            // for 4 directions, turns the possible one
            final int DIRECTIONS = 4;
            for (int ix = 0; ix < DIRECTIONS; ix++){

            }

        }

        // check if can have another move?
        public boolean isEndGame(){
            // check if there is other move for the opponent color

            return false;
        }

        public int getNumberOfDisc(){
            int discNum = 0;
            return discNum;
        }

    }


    protected char color;
    protected static Board board;
    private static int ID = 0;

    /**
     * initialize the player, the first one is B and the second one is W
     * @param size size of the grid
     */
    Player(int size){
        ID++;
//        color = (ID == 1) ? 'B' : 'W';
        if (ID == 1){
            color = 'B';
        }
        else{
            color = 'W';
        }
        board = new Board(size);
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
}
