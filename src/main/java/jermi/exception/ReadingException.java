package jermi.exception;

public class ReadingException extends JermiException {

    public ReadingException(String message) {
        super("An error has occurred while loading: " + message);
    }
}
