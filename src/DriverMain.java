import java.util.Scanner;

public class DriverMain {
    public static void main(String [] args){
        final int NUMBER_OF_PLAYER = 2;
        // get game types
        int gameType = chooseGameType();

        // choose board size
        int boardSize = 0;
        System.out.println("Please enter the board size (must be an even number greater than or equal to 2 " +
                "or the board will have size 4 in default):  ");
        boardSize = getInt();


        // create 2 players
        Player[] players = new Player[NUMBER_OF_PLAYER];

        if (gameType == 1){
            players[0] = new User(boardSize);
            players[1] = new Computer(boardSize);
        }
        else if (gameType == 2){
            players[0] = new Computer(boardSize);
            players[1] = new Computer(boardSize);
        }
        else{
            players[0] = new User(boardSize);
            players[1] = new User(boardSize);
        }

        // game begins by 2 players take turn making a move, B move first
        // after each move, check for score
        // is game end?
        // is there any move that turn the rows or columns?, if yes, then turn them
        // display the new board

        int counter = 0;    // count of the number of move

        // loop until there is no valid move means game end
        do{
            // display board
            players[0].board.displayBoard();

            // make new move. turn other moves
            players[counter%2].newMove();

            // check for score


            counter++;
        }
        while(!players[0].board.isEndGame() && !players[1].board.isEndGame());


    }


    public static int chooseGameType(){
        Integer choice = 0;

        // get user option
        do {
            System.out.println("1. Player versus Computer");
            System.out.println("2. Computer versus Computer");
            System.out.print("3. Player versus Player\n" + "Your choice:  ");

            // get user's option
            choice = checkRange(getInt(), 1, 3);
        }
        while (choice == null);
        return choice;
    }


    public static Integer getInt(){
        Scanner scanner = new Scanner(System.in);
        String arg = scanner.next();
        Integer x = 0;
        try{
            x = Integer.parseInt(arg);
        }
        catch(NumberFormatException e){
            x = null;
        }
        return x;
    }

    public static Integer checkRange(Integer x, int lowerBound, int upperBound){
        if (x == null || x < lowerBound || x > upperBound) {
            x = null;
        }
        return x;
    }

}
