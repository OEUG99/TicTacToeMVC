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
                return '#';
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

    public void drawBoard(int[][] board) {
        StringBuilder str = new StringBuilder();
        for (int[] row: board) {

            for (int col: row){
                str.append(convertIntToChar(col));
            }
            str.append("\n");
        }
        System.out.println(str);
        System.out.println( " ------- ");
    }

    public void drawMessage(String message) {
        System.out.println(message + "\n");
    }
}
