import java.text.ParseException;

/** Custom sub-class of ParseException to denote a
 * specific parsing issue with dates instead of just
 * general text. This is for future-proofing when we might
 * actually have to parse general text next time.
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
