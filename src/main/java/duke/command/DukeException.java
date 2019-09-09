package duke.command;

/**
 * Provides a custom DukeException objects describing exceptions arising from Duke.
 */
public class DukeException extends Exception {

    /** Stores the description of the error. */
    String error;

    /**
     * Creates a new DukeException.
     * @param error The string describing the error.
     */
    public DukeException(String error) {
        this.error = error;
    }

    /**
     * Returns a string describing the error.
     * @return A string describing the error.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! " + error + "\n";
    }
}
