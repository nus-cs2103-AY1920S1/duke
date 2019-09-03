/**
 * Deal with errors such as incorrect inputs entered by the user.
 */
public class DukeException extends Exception {

    /**
     * Initialises DukeException with the error message
     * @param message The Error Message
     */
    public DukeException (String message) {
        super(message);
    }

    /**
     * Returns a DukeException indicating that the description is empty
     * @return The DukeException
     */
    public static DukeException emptyDescription () {
        return new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
