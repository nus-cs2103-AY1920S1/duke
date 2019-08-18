public class InvalidDescriptionException extends DukeException {

    InvalidDescriptionException(String message) {
        super(String.format("The description of a %s cannot be empty.", message));
    }
}
