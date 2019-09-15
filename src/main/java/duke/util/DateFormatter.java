package duke.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Formats date inputs.
 */
public class DateFormatter {
    /** Default date format. **/
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d/M/yyyy HHmm");

    /**
     * Parses a given input to return a date.
     *
     * @param input Input string
     * @return Date
     * @throws ParseException If the parsing fails.
     */
    public static Date parse(String input) throws ParseException {
        return DATE_FORMAT.parse(input);
    }

    /**
     * Formats a given date to return a string.
     *
     * @param date Input date
     * @return String
     */
    public static String format(Date date) {
        return DATE_FORMAT.format(date);
    }
}
