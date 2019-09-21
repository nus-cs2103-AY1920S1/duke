package bot.duke.exception;

public class DukeEventException extends DukeException {

    public static final String FORMAT_ERROR_MSG = "☹ OOPS!!! The format of event is wrong! The format is:\n"
            + "event <Event Details> /at dd/MM/yyyy HH:mm - dd/MM/yyyy HH:mm";

    public static final String EMPTY_DETAILS_ERROR_MSG = "☹ OOPS!!! The description of a event cannot be empty.";

    /**
     * Constructs the DukeEventException object with a custom error message.
     *
     * @param message Custom error message
     */
    public DukeEventException(String message) {
        super(message);
    }

}
