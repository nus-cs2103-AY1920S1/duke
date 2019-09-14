/**
 * A DukeException for when the user inputs an empty task description.
 */
class EmptyDescriptionException extends DukeException {

    EmptyDescriptionException(String msg) {
        super(msg);
    }

}
