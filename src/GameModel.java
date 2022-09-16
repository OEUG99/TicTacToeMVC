import java.util.Arrays;
import java.util.Collections;

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

    public void updateBoard(int pos, int current_turn){
        int col = (pos -1 ) % 3;
        int row = (int) floor(pos / 3.5);

        this.board[row][col] = current_turn;
    }
}
