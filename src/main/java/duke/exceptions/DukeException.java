package duke.exceptions;

public class DukeException extends Exception {

    /**
     * Constructor for Duke Exceptions.
     *
     * @param message takes in the error and
     *     prints it out to the user.
     */
    public DukeException(String message) {
        super(message);
    }
}