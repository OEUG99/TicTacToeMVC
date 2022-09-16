import java.util.Scanner;

public class GameControl {

    private final GameView View;
    private final GameModel Model;

    private int current_turn;

    public GameControl(GameView v, GameModel m){
        this.View = v;
        this.Model = m;
        this.current_turn = 1; // start with player 1.
    }
    
    public void setGamemode(int gamemode) {
    }

    private int getInput(String message){
       int input;

       View.drawMessage(message);

       Scanner scanner = new Scanner(System.in);
       input = scanner.nextInt();

        return input;
    }

    private void changeTurn(){
        switch(current_turn){
            case 1:
                current_turn = 2;
                break;
            case 2:
                current_turn = 1 ;
                break;
        }
    }


    public void update() {
        //Model.updateBoard(0,0,2);
        int PlayersMove = getInput("Player " + current_turn + ", please enter a move(1-9) ");
        Model.updateBoard(PlayersMove,current_turn);
        changeTurn();
        View.drawBoard(Model.getBoard());
    }
}
