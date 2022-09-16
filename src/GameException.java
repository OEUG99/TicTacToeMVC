public class GameException extends Exception {
    public GameException(String e){
        super(e);
    }

    public static class ViewInitializationException extends GameException {

        public ViewInitializationException(String e) {
            super(e);
        }
    }

}
