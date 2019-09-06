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

    /**
     * Parses text from a string to produce a {@link Date}.
     *
     * @param text A {@link String}, part of which should be parsed.
     * @return A {@link Date} parsed from the string. In case of error, returns null.
     * @throws ParseException if the beginning of the text cannot be parsed.
     */
    public static Date parse(String text) throws ParseException {
        return formatter.parse(text);
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
