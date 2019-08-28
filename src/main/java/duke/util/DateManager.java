package duke.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * Helper class containing static methods to convert Date object to and from the string representation.
 */
public class DateManager {
    /** Formatter for formatting and parsing date according to the "dd/MM/yyyy HHmm" pattern. */
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Returns a Date object with representing the date given in the dateString.
     *
     * @param dateString String representing a date in the "dd/MM/yyyy HHmm" format
     * @return Date object the string is representing.
     * @throws DukeException If dateString is not in the correct format.
     */
    public static Date stringToDate(String dateString) throws DukeException {
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            throw new DukeException("Please specify the date in the correct format:\n     DD/MM/YYYY HHMM");
        }
    }

    /**
     * Returns a String representing the Date object.
     *
     * @param date Date object to be converted to String.
     * @return String representing the Date in the "dd/MM/yyyy HHmm".
     */
    public static String dateToString(Date date) {
        return formatter.format(date);
    }
}
