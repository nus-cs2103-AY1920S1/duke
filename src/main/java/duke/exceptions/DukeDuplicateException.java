package duke.exceptions;

/**
 * It is a Exception class to deal with the duplicate tasks input by users.
 */
public class DukeDuplicateException extends Exception {

    /**
     * Constructor of the class.
     */
    public DukeDuplicateException() {
        super("There is already a same task in the list!");
    }
}
