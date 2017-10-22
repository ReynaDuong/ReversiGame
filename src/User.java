import java.util.Scanner;

public class User extends Player {

    User(int size){
        super(size);
    }


    private Integer validateInput(String arg){

        Integer x = 0;
        try{
            x = Integer.parseInt(arg);
            if (x < 0 || x >= board.getSize()){
                x = null;
            }
        }
        catch(NumberFormatException e){
            x = null;
        }
        return x;
    }


    @Override
    void newMove() {
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
            row = validateInput(rowInput);
            col = validateInput(colInput);

            // check for valid move on board
        }
        while (row == null || col == null || board.isValidMove(row, col));



    }
}
