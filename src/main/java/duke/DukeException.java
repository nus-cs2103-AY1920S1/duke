package duke;

/**
 * Represents an exception in project duke.
 */
public class DukeException extends Exception {

    /**
     * Initiates a DukeException object.
     * @param s exception message
     */
    public DukeException(String s) {
        super(s);
    }

    /**
     * Initiates a DukeException object.
     */
    public DukeException() {
    }

}
