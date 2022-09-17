public class TicTacToe {

    private static int argumentParser(String[] args){
        if (args.length == 0){
            return 1; // returns 1 for two human mode
        }

        if (args[0].equals("-c")){
            return 2; // return 2 for two computer mode.
        }

        String s = String.format("%s %s", args[0], args[1]);
        switch(s) {
            case "-c 1":
                return 3; // return 3 for computer is player 1, human player 2
            case "-c 2":
                return 4; // return 4 for human player 1, computer player 2
        }

        return 1; // return 1 for a default.
    }

    public static void main(String[] args) {
        int gamemode = argumentParser(args);
        boolean isGameAlive = true;

        // Creating MVC components:
        GameModel Model = new GameModel();
        GameView View = new GameView();
        GameControl Control = new GameControl(View, Model);

        // Initializing the view;
        View.init(Model.getBoard());
        Control.setGamemode(gamemode);

        while(isGameAlive){
            if(Control.update()) {
                isGameAlive = false;
            };
        }

    }

}