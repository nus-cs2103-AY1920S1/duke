package exceptions;

/**
 * Exceptions.DukeException class used to create the throwable Core.Duke Exceptions
 * particular to Core.Duke.
 */

public class DukeException extends Exception {

    /**
     * Creates a new throwable Exceptions.DukeException.
     * @param message Message to be displayed when the Exceptions.DukeException is encountered.
     */
    public DukeException(String message) {
        super(message);
    }
}