public class EmptyDescriptionException extends DukeException {

    EmptyDescriptionException(String message) {
        super(String.format("The description of %s cannot be empty.", message));
    }
}
