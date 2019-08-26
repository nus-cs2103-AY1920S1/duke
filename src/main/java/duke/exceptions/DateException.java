/**
 * This exception is thrown when there is error in the input for dates and times regarding the Deadline and Event tasks.
 */
package duke.exceptions;

public class DateException extends Exception {
    public DateException(String message) {
        super(message);
    }
}
