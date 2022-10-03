
public class GameView {

    /**
     * <p>This method is to initialize the view.
     *
     * @param board draws the board.
     */
    public void init(int[][] board){
        // anything else for initialization would go here, for example
        // if using a graphic library this is where you would initialize
        // window sizes etc.
        drawBoard(board);
    }

    private char convertIntToChar(int x){

        switch(x) {
            case -1:
                return 'X';
            case 1:
                return 'O';
            case 0:
                return ' ';
            default:
                assert true;
        }
        return ' ';
    }



    private int convertToProperPlayerNumber(int x){
        return (x == -1 ? 1 : 2); // if the winning turn was -1 then it's p1, if it's not then p2
    }

    /**
     * <p>This method is used to draw the gameboard.
     * Note that it takes our data and onverts it to something viewable,
     * the internal array is made up of -1 and 1s, so we swap them for 'x' and 'O' when printing.
     * @param board the baord we want to draw.
     */
    public void drawBoard(int[][] board){
        // Note:
        String header = String.format("Game Board: %c %c Positions:", 9, 9); // 9 = ascii tab-space, %c gets ascii char from parameter.
        String r1 = String.format(" %c | %c | %c %c %c 1 | 2 | 3 ", convertIntToChar(board[0][0]),
                                                                                    convertIntToChar(board[0][1]),
                                                                                    convertIntToChar(board[0][2]),
                                                                                    9,
                                                                                    9);

        String r2 = String.format(" %c | %c | %c %c %c 4 | 5 | 6 ", convertIntToChar(board[1][0]),
                                                                                    convertIntToChar(board[1][1]),
                                                                                    convertIntToChar(board[1][2]),
                                                                                    9,
                                                                                    9);

        String r3 = String.format(" %c | %c | %c %c %c 7 | 8 | 9 ", convertIntToChar(board[2][0]),
                                                                                    convertIntToChar(board[2][1]),
                                                                                    convertIntToChar(board[2][2]),
                                                                                    9,
                                                                                    9);
        String lineDivider = String.format("----------- %c %c -----------", 9, 9);

        drawMessage(header + "\n");
        drawMessage(r1 + "\n");
        drawMessage(lineDivider + "\n");
        drawMessage(r2 + "\n");
        drawMessage(lineDivider + "\n");
        drawMessage(r3 + "\n");
        drawMessage("\n");

    }

    /**
     * <p>Prints out a string.
     *This method also filters out any non-ascii input from the string. </p>
     * @param message The message we want to print.
     */
    public void drawMessage(String message) {
        String result = removeNonAscii(message);
        System.out.print(result);
    }


    /**
     * <p>Prompts the user to enter a move.
     *This method also filters out any non-ascii input from the string. </p>
     * @param current_turn Who's turn it is. -1 is player 1, 1 is player 1.
     */
    public void drawInputMessage(int current_turn){
        String message = "Player " + convertToProperPlayerNumber(current_turn) + ", please enter a move(1-9): ";
        drawMessage(message);
    }

    /**
     * <p>Displays a winning message.
     *This method also filters out any non-ascii input from the string. </p>
     * @param s The string we want to print.
     * @param current_turn the turn for the player who won. -1 is player 1, 1 is player 1.
     */
    public void drawWinMessages(String s, int current_turn){
        drawMessage(s + convertToProperPlayerNumber(current_turn) + "\n");
    }

    private String removeNonAscii(String s){
        // Using pattern matching to remove all non ASCII symbols.
        // front of string, unicode property of ASCII 0-127, entire string
        return s.replaceAll("[^\\p{ASCII}]*$", "");
    }
}

