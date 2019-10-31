package duke.exception;

public class DukeException extends Exception {

    /**
     * Creates an exception specific for Duke.
     *
     * @param s a string containing description of the problem
     */
    public DukeException(String s) {
        super(s);
    }
}
