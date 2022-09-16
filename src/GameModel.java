import static java.lang.Math.abs;
import static java.lang.Math.floor;

public class GameModel {

    private final int[][] board;


    public GameModel(){
        this.board = new int[3][3];
    }

    public int[][] getBoard(){return board;}


    public void updateBoard(int row, int col, int state) {
        this.board[row][col] = state;
    }

    public boolean updateBoard(int pos, int current_turn){
        boolean result = false;
        int col = (pos -1 ) % 3;
        int row = (int) floor(pos / 3.5);

        if ( !(pos > 0 && pos < 10) ) { // if input is not in range 1 through 9
            return false;
        }

        if ( abs(this.board[row][col]) != 1){ // if a play has already been made.
            this.board[row][col] = current_turn;
            result = true;
        }

        return result;
    }

    public boolean scanForWin(){
        int[][] board = getBoard();
        boolean result = false;

        for (int i = 0; i < 3; i++) {
            int csum = 0;
            int rsum = 0;
            for (int j = 0; j < 3; j++) {
                csum += board[i][j];
                rsum += board[j][i];

                if ( (abs(csum) == 3) || (abs(rsum) == 3) ) {
                    // View.drawMessage("winner is " + current_turn);
                    result = true;
                }
            }
        }
        if (!result) { // if we do not already have a winner, scan the Diagnoles.
            result = scanDiagnoles();
        }
        return result;
    }

    private boolean scanDiagnoles(){
        int[][] board = getBoard();
        int d1_sum = board[0][0] + board[1][1] + board[2][2];
        int d2_sum = board[2][0] + board[1][1] + board[0][2];

        return (abs(d1_sum) == 3) || (abs(d2_sum) == 3);
    }


}
