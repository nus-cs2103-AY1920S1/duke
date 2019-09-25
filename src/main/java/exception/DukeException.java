package exception;

/**
 * General exception for all Duke related exceptions that other exceptions should extend.
 */
public class DukeException extends Exception {

    public static final String EMPTY_TODO_DESCRIPTION_MESSAGE = "OOPS!!! The description of a todo cannot be empty.";
    public static final String UPDATE_STATE_EXCEPTION_MESSAGE = "Exception while updating state!";
    public static final String DELETE_PARAMETER_EXCEPTION_MESSAGE = "Invalid parameter! Try the format: delete (task "
            + "number)";
    public static final String RESCHEDULE_PARAMETER_EXCEPTION_MESSAGE = "Invalid parameter! Try the format: reschedule"
            + " (task number) (dd/mm/yyyy hhmm)";
    public static final String DONE_PARAMETER_EXCEPTION = "Invalid parameter! Try the format: done (task number)";

    /**
     * Constructor for DukeException.
     * @param exceptionMsg Message that will likely be displayed to the user if exception is caught to
     *                     explain what caused the issue
     */
    public DukeException(String exceptionMsg) {
        super(exceptionMsg);
    }
}