public class GameException extends Exception {
    public GameException(String e){
        super(e);
    }

    public static class ViewInitializationException extends GameException {

        public ViewInitializationException(String e) {
            super(e);
        }
    }

    public static class ViewDataConversionException extends GameException {
        public ViewDataConversionException (String e) {
            super("Unable to convert int to char: " + e);
        }

        public ViewDataConversionException(){
            super("Unable to convert int to char: ");
        }
    }
}
