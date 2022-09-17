import java.util.Random;
import java.util.Scanner;

public class GameControl {

    private final GameView View;
    private final GameModel Model;

    private int current_turn;

    private int gamemode;

    public GameControl(GameView v, GameModel m){
        this.View = v;
        this.Model = m;
        this.current_turn = -1; // start with player 1.
        this.gamemode = 1; // 2 players will the default gamemode.
    }
    
    public void setGamemode(int gamemode) {
        this.gamemode = gamemode;
    }

    private int computeAIMove(boolean isUnbeatable){
        int winningPos = Model.getWinningPos(); // agnostic of which player.

        if (winningPos != 0) { // if there is a winning move for either players then take it.
            System.out.println(winningPos);
            return winningPos;
        } else if (Model.getBoard()[1][1] == 0){ // if center of the board is avaliable, take it.
            return 5;
        } else {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        }
    }


    private int getInput(String message){
       int input;

        View.drawMessage(message);

        switch(gamemode){
            case 2: // two computer mode:
                input = computeAIMove(false);
                break;
            case 3: // computer is player 1, human player 2
                input = 0;
                break;
            case 4: // human player 1, computer player 2
                input = 0;
                break;
            default:
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextInt();
                break;
        }


        return input;
    }

    private void changeTurn(){
        current_turn *= -1;
    }


    private boolean checkForWinner() {
        boolean result = false;
        if (Model.scanForWin()){
            View.drawMessage("winner is " + current_turn);
            result = true;
        } else if (Model.scanForDraw()) {
            View.drawMessage("There is a draw!");
            result = true;
        }
        return result;
    }

    public boolean update() {

        while (true){ // Looping to insure input is valid.
            int PlayersMove = getInput("Player " + current_turn + ", please enter a move(1-9) ");
            if (!Model.updateBoard(PlayersMove,current_turn)) { // if the input is not valid,
                View.drawMessage("invalid input, please try again.");
            } else {
                break;
            }
        }

        changeTurn();
        View.drawBoard(Model.getBoard());

        return checkForWinner();
    }


}
