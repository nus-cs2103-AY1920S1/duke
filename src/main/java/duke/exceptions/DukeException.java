/**
 * This exception is thrown when there is any general error to the input commands to the Duke chat bot.
 */
package duke.exceptions;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
