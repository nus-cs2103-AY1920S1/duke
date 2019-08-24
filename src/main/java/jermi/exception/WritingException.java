package jermi.exception;

public class WritingException extends JermiException {

    public WritingException(String message) {
        super("An error has occurred while saving: " + message);
    }
}
