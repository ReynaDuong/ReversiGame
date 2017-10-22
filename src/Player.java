abstract public class Player {

    class Board {
        char[][] grid;

        Board(){
            int defaultSize = 4;

            grid = new char [defaultSize][defaultSize];
            for (int ix = 0; ix < defaultSize; ix++){
                for(int iy = 0; iy < defaultSize; iy++){
                    grid[ix][iy] = '_';
                }
            }

        }

        /**
         * create a new board and initialize with _
         * @param size the size of the grid
         */
        Board(int size){
            grid = new char [size][size];

            for (int ix = 0; ix < size; ix++){
                for(int iy = 0; iy < size; iy++){
                    grid[ix][iy] = '_';
                }
            }


        }

        int getSize(){
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

        boolean isValidMove(int row, int col){

            return false;
        }

        /**
         * add a new valid move to the grid
         * @param row row number
         * @param col column number
         */
        void addMove(int row, int col){

        }

        void turnColor(){
        }


    }

    char color;
    static Board board;
    static int ID = 0;


    /*
    set the color of the player and create a new grid to play on
     */
    Player(int size){
        ID++;
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
    abstract void newMove();
}
