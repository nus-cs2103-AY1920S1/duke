import java.text.ParseException;

/** Custom sub-class of ParseException to denote the
 * specific parsing issue with dates instead of just
 * general text.
 */
public class DateParseException extends ParseException {

    /** Constructor that takes in an error message and
     * returns a DateParseException object.
     * @param error_message description of error.
     */
    public DateParseException(String error_message, int errorOffset) {
        super(error_message, errorOffset);
    }
}
