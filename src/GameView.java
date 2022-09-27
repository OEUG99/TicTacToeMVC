public class GameView {
    public void init(int[][] board){
        drawBoard(board);
        // anything else for initialization would go here, for example
        // if using a graphic library this is where you would initialize
        // window sizes etc.
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

    private char convertToProperPlayerNumber(int x){

        switch(x) {
            case -1:
                return '1';
            case 1:
                return '2';
            default:
                assert true;
        }
        return ' ';
    }


    public void drawDebugBoard(int[][] board) {
        StringBuilder str = new StringBuilder();
        for (int[] row: board) {

            for (int col: row){
                str.append(col);
            }
            str.append("\n");
        }
        System.out.println(str);
    }

    public void drawBoard(int[][] board){
        System.out.println("Game Board:                     Positions:");
        String r1 = String.format(" %s | %s | %s                      1 | 2 | 3 ", convertIntToChar(board[0][0]),
                                                                                    convertIntToChar(board[0][1]),
                                                                                    convertIntToChar(board[0][2]));

        String r2 = String.format(" %s | %s | %s                      4 | 5 | 6 ", convertIntToChar(board[1][0]),
                                                                                    convertIntToChar(board[1][1]),
                                                                                    convertIntToChar(board[1][2]));

        String r3 = String.format(" %s | %s | %s                      7 | 8 | 9 ", convertIntToChar(board[2][0]),
                                                                                    convertIntToChar(board[2][1]),
                                                                                    convertIntToChar(board[2][2]));

        System.out.println(removeNonAscii(r1));
        System.out.println(removeNonAscii(r2));
        System.out.println(removeNonAscii(r3));
        System.out.print(removeNonAscii("\n"));

    }


    public void _drawBoard(int[][] board) {
        StringBuilder str = new StringBuilder();
        for (int[] row: board) {

            for (int col: row){
                str.append(convertIntToChar(col));
            }
            str.append("\n");
        }
        System.out.println(removeNonAscii(String.valueOf(str)));
        System.out.println( removeNonAscii(" ------- "));
    }

    public void drawMessage(String message) {
        String result = removeNonAscii(message);
        System.out.print(result);
    }

    public void drawInputMessage(int current_turn){
        String message = "Player " + convertToProperPlayerNumber(current_turn) + ", please enter a move(1-9): ";
        String result = removeNonAscii(message);
        System.out.print(result);
    }

    public void drawWinMessages(String s, int current_turn){
        int convertTurn = (current_turn == -1 ? 1 : 2); // if the winning turn was -1 then it's p1, if it's not then p2
        drawMessage(s + convertTurn);
        System.out.print(removeNonAscii("\n"));
    }

    private String removeNonAscii(String s){
        String result = s.replaceAll("[^\\p{ASCII}]*$", ""); // front of string, unicode property of ASCII 0-127, entire string
        return result;
    }
}

