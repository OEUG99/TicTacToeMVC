
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

        while(isGameAlive){
            if(Control.update()) {
                isGameAlive = false;
            }
        }

    }

}