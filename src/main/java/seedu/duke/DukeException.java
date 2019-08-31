package seedu.duke;

/**
 * DukeException class is a custom Exception class.
 */
public class DukeException extends Exception {

    /**
     * Current implementation will only construct the String message.
     * @param message String that will be printed during exception handling.
     */
    public DukeException(String message){
        super(message);
    }
}
