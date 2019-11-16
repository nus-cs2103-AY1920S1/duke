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

    public static DukeException outOfBounds () {
        return new DukeException("☹ OOPS!!! The number you inputted is out of bounds \uD83D\uDE22");
    }

    public static DukeException couldNotSave () {
        return new DukeException("☹ OOPS!!! Your task could not be saved/updated/deleted \uD83D\uDE22");
    }
}
