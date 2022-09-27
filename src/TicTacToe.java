

/*
TicTacToe written in Java using a pure MVC design pattern.
The benefit to using MVC is that the viewing code, data modeling/storage, and input/controlling
is all isolated.

In theory, you can swap these components with others. For example, the view can be swapped out with a view for a graphics
library instead of ascii, and all the rest of the code will function the same.
 */
public class TicTacToe {

    public static void main(String[] args) {
        boolean isGameAlive = true;

        // Creating the parser;
        CLIParser parser = new CLIParser(args);

        // Creating MVC components:
        GameModel Model = new GameModel();
        GameView View = new GameView();
        GameControl Control = new GameControl(View, Model);

        // Initializing the view;
        View.init(Model.getBoard());

        Control.setGamemode(parser.getCFlag());
        Control.setAIDificulty(parser.getAFlag());

        while(isGameAlive){
            if(Control.update()) {
                isGameAlive = false;
            }
        }

    }

}