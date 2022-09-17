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


    public int convertCordsToPos(int x, int y){
      return (x * 3) + (y % 3) +1;

          /*
        if (x == 0 && y == 0){
            return 1;
        } else if (x == 0 && y == 1){
            return 2;
        } else if (x == 0 && y == 2){
            return 3;
        } else if (x == 1 && y == 0){
            return 4;
        } else if (x == 1 && y == 1) {
            return 5;
        } else if (x == 1 && y == 2){
            return 6;
        } else if (x == 2 && y == 0){
            return 7;
        } else if (x == 2 && y == 1){
            return 8;
        } else if (x== 2 && y == 2){
            return 9;
        }

               */

    };


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

    public boolean scanForDraw() {
        boolean isPlayableMove = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (getBoard()[i][j] == 0 || getBoard()[j][i] == 0) {
                    isPlayableMove = true;
                }
            }
        }
        return !(isPlayableMove); // If there is no playable move its a draw.
    }

    private boolean scanDiagnoles(){
        int[][] board = getBoard();
        int d1_sum = board[0][0] + board[1][1] + board[2][2];
        int d2_sum = board[2][0] + board[1][1] + board[0][2];

        return (abs(d1_sum) == 3) || (abs(d2_sum) == 3);
    }

    public int getWinningPos() {

        int[][] board = getBoard();
        int result = 0; // if no winning spot return 0;

        for (int i = 0; i < 3; i++) {
            int csum = 0;
            int rsum = 0;
            for (int j = 0; j < 3; j++) {
                csum += board[i][j];
                rsum += board[j][i];


                if ( (abs(csum) == 2) ) {
                    if (board[i][j] != 0){
                        continue;
                    }
                    result = convertCordsToPos(i, j);
                } else if ( (abs(rsum) == 2) ) {
                    if (board[j][i] != 0){
                        continue;
                    }
                    result = convertCordsToPos(j, i);
                }
            }
        }

        // todo: check diangles
        return result;

    }


}
