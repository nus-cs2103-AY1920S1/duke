/**
 * An exception that occurs when Duke is unable to decipher which command is given.
 */

public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
