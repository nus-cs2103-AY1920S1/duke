package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles the parsing of dates from String formats into Date objects.
 */
public class DateParser {
    /**
     * Array of date formats that the application is able to parse. A valid date
     * can take any of the following formats:
     * 1. EEE, d MMM yy, HH:mm
     * 2. d-MM-yyyy HH:mm
     * 3. dMMyy HHmm
     * 4. ISO date time (yyyy-MM-ddTHH:mm:ss)
     * where EEE is the day of week (e.g. Mon, Fri).
     */
    public static final DateTimeFormatter[] DATE_FORMATS = {
        DateTimeFormatter.ofPattern("EEE, d MMM yy, HH:mm"),
        DateTimeFormatter.ofPattern("d-MM-yy HH:mm"),
        DateTimeFormatter.ofPattern("dMMyy HHmm"),
        DateTimeFormatter.ISO_DATE_TIME
    };

    /**
     * Returns the default format to be used in formatting dates.
     *
     * @return  The default DateTimeFormatter to be used.
     */
    public static DateTimeFormatter getDefaultFormat() {
        return DATE_FORMATS[0];
    }

    /**
     * Returns a Date corresponding to the date represented by the given String.
     * If the input format is invalid (does not match any of the date formats
     * specified in DateParser.DATE_FORMATS), returns the Date representing the
     * current instant.
     *
     * @param date String representing a date.
     * @return Date corresponding to the given date String, or the
     *         current instant.
     */
    public static LocalDateTime parse(String date) {
        for (DateTimeFormatter format : DATE_FORMATS) {
            try {
                return LocalDateTime.parse(date, format);
            } catch (DateTimeParseException e) {
                // do nothing and try the next format
            }
        }
        return LocalDateTime.now();
    }
}
