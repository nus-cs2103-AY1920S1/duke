/**
 * A DukeException class that extends from Exception class.
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException object.
     *
     * @param mesg A string representing the exception.
     */
    public DukeException(String mesg) {
        super(mesg);
    }
}