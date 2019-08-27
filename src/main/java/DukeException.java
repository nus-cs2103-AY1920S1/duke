/** Exception class for representing cases where
 * an input command is not recognized by the Duke
 * program.
 */
public class DukeException extends Exception {
    /**
     * Basic constructor for the DukeException class
     * that takes in an error message.
     * @param err_message error message to be associated.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
