package duke.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Formats a date and time format into a string.
 */
public class DateFormatter {

    /**
     * Returns a formatted string from the <code>Date</code> object in <code>dd/MM/yy HHmm</code> format.
     * @param date the <code>Date</code> object to be formatted
     * @return a string representation of the <code>Date</code> object
     */
    public static String format(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy HHmm").format(date);
    }
}
