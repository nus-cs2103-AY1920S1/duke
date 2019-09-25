package duke.task;

/**
 * The DeadlineException class handles any incorrect deadline input by the user,
 * and prompts them to enter in the correct format.
 */
public class DeadlineException extends Exception {
    private static final String MISSING_DESCRIPTION =
            " OOPS!!! The description of a deadline cannot be empty.";
    private static final String MISSING_DATE =
            " OOPS!!! The date of a deadline cannot be empty.";
    private static final String MISSING_BOTH =
            " OOPS!!! The description or date of an event cannot be empty.";
    private String error;
    private int type; // 1 = empty desc, 2 = no date, 3 = either

    /**
     * Constructs a EventException object.
     * @param message the message linked to the exception
     * @param type the type of error that is to be handled in this class
     */
    public DeadlineException(String message, int type) {
        this.error = message;
        this.type = type;
    }

    /**
     * Gets the error message tied to the exception found.
     * @return a string of error message for the exception
     * found in the Deadline object
     */
    public String getMessage() {
        if (this.type == 1) {
            return MISSING_DESCRIPTION;
        } else if (this.type == 2) {
            return MISSING_DATE;
        } else {
            return MISSING_BOTH;
        }
    }
}
