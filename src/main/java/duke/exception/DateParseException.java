package duke.exception;

import java.text.ParseException;

/**
 * Custom sub-class of ParseException to denote a
 * specific parsing issue with dates instead of just
 * general text. This is for future-proofing when we might
 * actually have to parse general text next time.
 */
public class DateParseException extends ParseException {

    /**
     * Returns a DateParseException object with an associated error message
     * and the error offset that triggered the exception during parsing.
     *
     * @param errorMessage description of error.
     */
    public DateParseException(String errorMessage, int errorOffset) {
        super(errorMessage, errorOffset);
    }
}
