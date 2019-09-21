package bot.duke.exception;

public class DukeDeadlineException extends DukeException {

    public static final String FORMAT_ERROR_MSG = "☹ OOPS!!! The format of Deadline is wrong! The format is:\n"
            + "Deadline <Deadline Details> /by dd/MM/yyyy HH:mm";

    public static final String EMPTY_DETAILS_ERROR_MSG = "☹ OOPS!!! The description of a Deadline cannot be empty.";

    /**
     * Constructs the DukeDeadlineException object with a custom error message.
     *
     * @param message Custom error message
     */
    public DukeDeadlineException(String message) {
        super(message);
    }

}
