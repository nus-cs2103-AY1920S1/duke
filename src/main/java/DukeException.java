/**
 * DukeException class used to create the throwable Duke Exceptions
 * particular to Duke.
 */

public class DukeException extends Exception {

    /**
     * Creates a new throwable DukeException.
     * @param message Message to be displayed when the DukeException is encountered.
     */
    public DukeException(String message) {
        super(message);
    }
}