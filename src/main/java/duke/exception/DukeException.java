package duke.exception;

/**
 * Class that contains exceptions specific to this program.
 */
public class DukeException extends Exception {

    /**
     * The error message to be printed out.
     */
    private String message;

    /**
     * Constructor that takes in the error message.
     * @param message The error message to be printed out.
     */
    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
