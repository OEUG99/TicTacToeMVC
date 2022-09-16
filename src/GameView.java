public class GameView {
    public void init(){

    }


    public void drawBoard(int[][] board) {
        StringBuilder str = new StringBuilder();
        for (int[] row: board) {

            for (int col: row){
                str.append(col);
            }
            str.append("\n");
        }
        System.out.println(str);
    }

    public void drawMessage(String message) {
        System.out.println(message + "\n");
    }
}
