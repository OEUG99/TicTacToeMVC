

/*
TicTacToe written in Java using a pure MVC design pattern.

Why MVC? Using this design pattern allows for the creation of a loosely coupled system, meaning the presentation, the processing,
and storage of data are seperate. This allows for easier maintability and porting.

A good example would be to subsuite the Ascii user interface with a graphics library. Since the view code is seperate,
to port the game to a graphics library you only have to modify the view component. This is nice because it means the
rest of your code will be unchanged and won't run into bugs as a result of porting to a graphics library.

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