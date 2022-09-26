import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.floor;

public class GameModel {

    private final int[][] board;
    private int current_turn;


    public GameModel() {
        this.board = new int[3][3];
        this.current_turn = -1; // player 1 represented as -1, player 2 represented as 1
                                // this allows us to easily convert between which player's turn it is via multiply by -1.
    }

    public int[][] getBoard() {
        return board;
    }

    public int getCurrent_turn(){
        return current_turn;
    }

    public void changeTurn(){
        current_turn *= -1;
    }


    public void updateBoard(int row, int col, int state) {
        this.board[row][col] = state;
    }


    private int convertCordsToPos(int x, int y) {
        return (x * 3) + (y % 3) + 1;
    }


    public boolean updateBoard(int pos, int current_turn) {
        boolean result = false;
        int col = (pos - 1) % 3;
        int row = (int) floor(pos / 3.5);

        if (!(pos > 0 && pos < 10)) { // if input is not in range 1 through 9
            return false;
        }

        if (abs(this.board[row][col]) != 1) { // if a play has already been made.
            this.board[row][col] = current_turn;
            result = true;
        }

        return result;
    }

    public boolean scanForWin() {
        int[][] board = getBoard();
        boolean result = false;

        for (int i = 0; i < 3; i++) {
            int csum = 0;
            int rsum = 0;
            for (int j = 0; j < 3; j++) {
                csum += board[i][j];
                rsum += board[j][i];

                if ((abs(csum) == 3) || (abs(rsum) == 3)) {
                    return true;
                }
            }
        }
         // if we do not already have a winner, scan the Diagnoles.
        return scanDiagnoles();
    }


    private boolean scanDiagnoles() {
        int[][] board = getBoard();
        int d1_sum = board[0][0] + board[1][1] + board[2][2];
        int d2_sum = board[2][0] + board[1][1] + board[0][2];

        return (abs(d1_sum) == 3) || (abs(d2_sum) == 3);
    }



    public int getWinningPos(int player) {
        int[][] board = getBoard();
        int result = 0; // if no winning spot return 0;

        // NOTE: This needs a major refactor, but functions as is, just looks terrible

        // start of rows, player = turn_number (internal array elements consist of -1 or 1)
        if ( (board[0][0] + board[0][1] == 2 * player) && board[0][2] == 0) {
            result = 3;
        } else if ( (board[0][0] + board[0][2] == 2 * player) && board[0][1] == 0) {
            result = 2;
        } else if ( (board[0][1] + board[0][2] == 2 * player) && board[0][0] == 0) {
            result = 1;
        }

        if ( (board[1][0] + board[1][1] == 2 * player) && board[1][2] == 0) {
            result = 6;
        } else if ( (board[1][0] + board[1][2] == 2 * player) && board[1][1] == 0) {
            result = 5;
        } else if ( (board[1][1] + board[1][2] == 2 * player) && board[1][0] == 0) {
            result = 4;
        }


        if ( (board[2][0] + board[2][1] == 2 * player) && board[2][2] == 0) {
            result = 9;
        } else if ( (board[2][0] + board[2][2] == 2 * player) && board[2][1] == 0) {
            result = 8;
        } else if ( (board[2][1] + board[2][2] == 2 * player) && board[2][0] == 0) {
            result = 7;
        } // end of rows

        //start of columns
        if ( (board[0][0] + board[1][0] == 2 * player) && board[2][0] == 0) {
            result = 7;
        } else if ( (board[2][0] + board[0][0] == 2 * player) && board[1][0] == 0) {
            result = 4;
        } else if ( (board[1][0] + board[2][0] == 2 * player) && board[0][0] == 0) {
            result = 1;
        }

        if ( (board[2][1] + board[1][1] == 2 * player) && board[0][1] == 0) {
            result = 2;
        } else if ( (board[0][1] + board[2][1] == 2 * player) && board[1][1] == 0) {
            result = 5;
        } else if ( (board[1][1] + board[0][1] == 2 * player) && board[2][1] == 0) {
            result = 8;
        }

        if ( (board[1][2] + board[2][2] == 2 * player) && board[0][2] == 0) {
            result = 3;
        } else if ( (board[0][2] + board[2][2] == 2 * player) && board[1][2] == 0) {
            result = 6;
        } else if ( (board[0][2] + board[1][2] == 2 * player) && board[2][2] == 0) {
            result = 9;
        } // end of verts


        // first diag
        if ( (board[0][0] + board[1][1] == 2 * player) && board[2][2] == 0) {
            result = 9;
        } else if ( (board[0][0] + board[2][2] == 2 * player) && board[1][1] == 0) {
            result = 5;
        } else if ( (board[2][2] + board[1][1] == 2 * player) && board[0][0] == 0) {
            result = 1;
        }

        // second diag
        if ( (board[2][0] + board[1][1] == 2 * player) && board[0][2] == 0) {
            result = 3;
        } else if ( (board[0][2] + board[1][1] == 2 * player) && board[2][0] == 0) {
            result = 7;
        } else if ( (board[0][2] + board[2][0] == 2 * player) && board[1][1] == 0) {
            result = 5;
        }

        return result;

    }


    public boolean scanForDraw() {
        boolean isPlayableMove = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (getBoard()[i][j] == 0 || getBoard()[j][i] == 0) { // 0 means empty space.
                    isPlayableMove = true;
                }
            }
        }
        return !(isPlayableMove); // If there is no playable move it's a draw.
    }

    public ArrayList<Integer> fetchPlayablePos() {
        int[][] board = getBoard();
        ArrayList<Integer> playablePos = new ArrayList<Integer>(0);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) { // 0 means empty space.
                    playablePos.add(convertCordsToPos(i,j));
                }
            }
        }

        return playablePos;
    }
}
