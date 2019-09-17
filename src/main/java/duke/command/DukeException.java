package duke.command;

/**
 * Handles any command that is not understandable by Duke.
 */
public class DukeException extends Exception {
    private static final String DUMB_DUKE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private String error;

    /**
     * Constructs a DukeException object.
     * @param msg the message linked to the exception
     */
    public DukeException(String msg) {
        this.error = msg;
    }

    /**
     * Gets the error message tied to the exception found.
     * @return a string of error message for an unrecognisable command for Duke.
     */
    public String getMessage() {
        return DUMB_DUKE;
    }
}
