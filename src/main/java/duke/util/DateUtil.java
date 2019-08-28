package duke.util;

import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a list of helper {@link Date} methods.
 */
public class DateUtil {
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Parses text from a {@link String} to produce a {@link Date}.
     *
     * @param text A {@link String} to be parsed.
     * @return A {@link Date} parsed from the {@link String}. In case of error, returns null.
     * @throws DukeException if text is null.
     */
    public static Date parse(String text) throws DukeException {
        try {
            return formatter.parse(text);
        } catch (ParseException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Formats the given {@link Date} into a date-time string.
     *
     * @param date the date-time value to be formatted into a date-time string.
     * @return the formatted date-time string.
     */
    public static String format(Date date) {
        return formatter.format(date);
    }
}
