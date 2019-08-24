package jermi.exception;

public class EmptyDescriptionException extends JermiException {

    public EmptyDescriptionException(String message) {
        super(String.format("The description of %s cannot be empty.", message));
    }
}
