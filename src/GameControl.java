import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GameControl {

    private final GameView View;
    private final GameModel Model;
    private int gamemode;

    public GameControl(GameView v, GameModel m){
        this.View = v;
        this.Model = m;
        this.gamemode = -1;
    }

    /**
     * <p>This method sets the gamemode.
     * if gamemode is set to 0, two AI will be used.
     * if gamemode is set to 1, the computer is player 1, and the human is player 2
     * if gamemode is set to 2, the computer is player 2, and the human is player 1
     * if gamemode is not one of the above, it will default to two human players.
     * See {@processPlayerMove() #ProcessPlayerMove} for 'gamemode' usage. </p>
     * @param gamemode determines if and how many players will be AI.
     */
    public void setGamemode(int gamemode) {
        this.gamemode = gamemode;
    }

    /**
     * <p> This method generates a move for any AI player.
     * @param isUnbeatable determines if the algorithm will generate an unbeatable move.
     * @return the position (1-9) that the algorithm determines is needed to be played.
     */
    private int computeAIMove(boolean isUnbeatable){
        int winningPos = Model.getWinningPos(Model.getCurrent_turn()); // agnostic of any player.
        int winningEnemyPos = Model.getWinningPos(Model.getCurrent_turn() * -1);

        ArrayList<Integer> playableSpaces;
        Random random = new Random();
        int ranChoice;


        if (winningPos != 0) { // if there is a winning move for the player take it
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
            playableSpaces = Model.fetchPlayablePos(); // gettinga list of possible moves, storing in array.
            ranChoice = random.nextInt((playableSpaces.size())); // random selecting an element from above array (equal distibution)
            return playableSpaces.get(ranChoice);
        }
    }

    /**
     * <p> This method gets user input for human players. </p>
     * @return the position (1-9) that the human determines is needed to be played.
     */
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

    /**
     * <p> This method processes player moves from {@GetUserInput #GetUserInput} and {@computeAIMove #ComputeAIMove}.
     * It is important to know that this function is <b>agnostic</b>, meaning that data from Human players and AI
     * is treated the same.
     * Additionally, this function uses the results of {@setGamemode} to determine if AI or player input is required. </p>
     * @return a position between (1-9)
     */
    private int processPlayerMove(){
       int input;
       int current_turn = Model.getCurrent_turn();

        View.drawInputMessage(current_turn);

        switch(gamemode){
            case 0: // two computer mode:
                input = computeAIMove(false);
                View.drawMessage(input + "\n");
                break;
            case 1: // computer is player 1, human player 2
                if (current_turn == -1) {
                    input = computeAIMove(false);
                    View.drawMessage(input + "\n");
                } else {
                    input = getUserInput();
                }
                break;
            case 2: // human player 1, computer player 2
                if (current_turn == 1) {
                    input = computeAIMove(false);
                    View.drawMessage(input + "\n");
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

    /**
     * <p> This method is for checking if there has been a winner or draw, as well as printing any needed text. </p>
     * @return boolean. if this is the final turn, it will return true killing the program.
     */
    private boolean endTurn(){
        boolean shouldEndGame = false;
        int current_turn = Model.getCurrent_turn();

        // drawing final board for tern
        View.drawBoard(Model.getBoard());

        if (Model.scanForWin()){
            View.drawWinMessages("The winner is player ", current_turn);
            shouldEndGame = true;
        } else if (Model.scanForDraw()) {
            View.drawMessage("There is a draw!\n");
            shouldEndGame = true;
        }

        // ending the turn:
        Model.changeTurn();

        /*
        if (shouldEndGame){
            View.drawMessage("--The final board--");
            View.drawBoard(Model.getBoard());
        }

         */

        return shouldEndGame;
    }

    /**
     * <p> This method represents one 'turn' of the game.
     * Most of this function has been abstracted into other functions, but this function
     * process's input from both players, then it tries to update the board, if it fails to do so
     * it tries to get the data gain. Note, this validity checking only really applies for invalid human
     * input. Before this function finalizes execution, {@endTurn} runs which tells the {@GameModel #Model} to change
     * which players turn it is via {@GameModel.current_turn}.
     * @return boolean. if this is the final turn, it will return true killing the program.
     */
    public boolean update() {
        int current_turn = Model.getCurrent_turn(); // This tells us which player's turn it is.

        while (true){ // Looping to insure input is valid.

            int PlayersMove = processPlayerMove(); // Fetching input for current turn player.

            if (!Model.updateBoard(PlayersMove,current_turn)) { // if the input is not valid,
                View.drawMessage("invalid input, please try again.");
                View.drawBoard(Model.getBoard());
            } else {
                break;
            }
        }
        return endTurn();
    }

}
