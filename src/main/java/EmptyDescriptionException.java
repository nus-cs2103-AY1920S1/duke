public class EmptyDescriptionException extends JermiException {

    EmptyDescriptionException(String message) {
        super(String.format("The description of %s cannot be empty.", message));
    }
}
