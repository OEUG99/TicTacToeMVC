import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GameControl {

    private final GameView View;
    private final GameModel Model;


    private int gamemode;
    private boolean isGameOver;

    public GameControl(GameView v, GameModel m){
        this.View = v;
        this.Model = m;
        this.gamemode = 1; // 2 players will the default gamemode.
        this.isGameOver = false;
    }
    
    public void setGamemode(int gamemode) {
        this.gamemode = gamemode;
    }

    private int computeAIMove(boolean isUnbeatable){
        int winningPos = Model.getWinningPos(Model.getCurrent_turn()); // agnostic of any player.
        int winningEnemyPos = Model.getWinningPos(Model.getCurrent_turn() * -1);

        ArrayList<Integer> playableSpaces;
        Random random = new Random();
        int ranChoice;


        if (winningPos != 0) { // if there is a winning move for the player take it
            // todo: there is a case where both players have a winning move, the algorithm fails.
            // as it does not always play the most ideal move to win teh game.
            return winningPos;
        }
        if (winningEnemyPos != 0) {
            return winningEnemyPos;
        }
        if (Model.getBoard()[1][1] == 0){ // if center of the board is avaliable, take it.
            return 5;
        }
        if (isUnbeatable){
            // todo - write unbeatable code
            playableSpaces = Model.fetchPlayablePos();
            ranChoice = random.nextInt((playableSpaces.size()));
            return playableSpaces.get(ranChoice);
        } else {
            // if there are no good moves for the beatable bot to make, then random select:
            playableSpaces = Model.fetchPlayablePos();
            ranChoice = random.nextInt((playableSpaces.size()));
            return playableSpaces.get(ranChoice);
        }
    }

    private int getUserInput(){
        int input;
        while (true){
            try{
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextInt();
                break;
            } catch (InputMismatchException e){
                View.drawMessage("Invalid input, please try again: ");
            }
        }

        return input;
    }


    private int getInput(String message){
       int input;
       int current_turn = Model.getCurrent_turn();

        View.drawMessage(message);

        switch(gamemode){
            case 0: // two computer mode:
                input = computeAIMove(false);
                break;
            case 1: // computer is player 1, human player 2
                if (current_turn == -1) {
                    input = computeAIMove(false);
                } else {
                    input = getUserInput();
                }
                break;
            case 2: // human player 1, computer player 2
                if (current_turn == 1) {
                    input = computeAIMove(false);
                } else {
                    input = getUserInput();
                }
                break;
            default:
                input = getUserInput();
                break;
        }


        return input;
    }

    private boolean checkForWinner() {
        int current_turn = Model.getCurrent_turn();
        boolean result = false;

        if (Model.scanForWin()){
            View.drawMessage("winner is " + (current_turn * -1));
            result = true;
        } else if (Model.scanForDraw()) {
            View.drawMessage("There is a draw!");
            result = true;
        }
        return result;
    }


    private boolean endTurn(){
        boolean shouldEndGame = false;
        int current_turn = Model.getCurrent_turn();

        if (Model.scanForWin()){
            View.drawMessage("winner is " + (current_turn * -1));
            shouldEndGame = true;
        } else if (Model.scanForDraw()) {
            View.drawMessage("There is a draw!");
            shouldEndGame = true;
        }

        // ending the turn:
        Model.changeTurn();

        // drawing final board for tern
        View.drawBoard(Model.getBoard());

        return shouldEndGame;

    }

    public boolean update() {
        int current_turn = Model.getCurrent_turn();

        while (true){ // Looping to insure input is valid.
            int PlayersMove = getInput("Player " + current_turn + ", please enter a move(1-9) ");
            if (!Model.updateBoard(PlayersMove,current_turn)) { // if the input is not valid,
                View.drawMessage("invalid input, please try again.");
                View.drawBoard(Model.getBoard());
            } else { // if input is valid, we can proceed with changing turn and ending the round.
                break;
            }
        }
        return endTurn();
    }

}
