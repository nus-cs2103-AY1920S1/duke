package duke.exception;

/**
 * This is a exception that occur when an invalid date is entered by the user.
 */
public class InvalidDateTimeException extends DukeException {

    /**
     * This is the string representation of the invalid date time.
     */
    private String dateTime;

    /**
     * Constructs a new invalid date time exception with the specified invalid date time entered by the user.
     * @param dateTime the invalid date time entered by the user
     */
    public InvalidDateTimeException(String dateTime) {
        super();
        this.dateTime = dateTime;
    }

    /**
     * Gets the invalid date time entered by the user.
     * @return the invalid date time entered by the user
     */
    public String getInvalidDateTime() {
        return dateTime;
    }

}
