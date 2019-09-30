package duke.format;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Teaching Duke to understand dates and times.
 * Converts original dateTime string to a different format.
 * eg. Converts 2/12/2019 1800 -> 2nd of December 2019, 6pm.
 */
public class DateTime {

    private static SimpleDateFormat reformatDeadline
            = new SimpleDateFormat("EEE MMM dd yyyy HH:mm a", Locale.ENGLISH);
    private static SimpleDateFormat reformatEventStart
            = new SimpleDateFormat("EEE MMM dd yyyy HH:mm a", Locale.ENGLISH);
    private static SimpleDateFormat reformatEventEnd
            = new SimpleDateFormat("HH:mm a", Locale.ENGLISH);

    public SimpleDateFormat getDeadline() {
        return reformatDeadline;
    }

    public SimpleDateFormat getEventStart() {
        return reformatEventStart;
    }

    public SimpleDateFormat getEventEnd() {
        return reformatEventEnd;
    }
}
