package duke;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Encapsulates date & time.
 */
public class DateTime {
    private final static String[] SUFFIXES = { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th", "th", "th", "th",
        "th", "th", "th", "th", "th", "th", "th", "th", "st", "nd", "rd", "th", "th", "th", "th", "th",
        "th", "th", "st" };

    /**
     * Parses date and time.
     *
     * @param input  Date and time to be parsed.
     * @return Date and time in the indicated output format if input format is a match, and original input otherwise.
     */
    public static String parse(String input) {
        Date dateTime;
        String output;
        SimpleDateFormat inputFormat = new SimpleDateFormat("d/MM/yyyy HHmm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMMMM yyyy, h:mm a");
        try {
            dateTime = inputFormat.parse(input);
        } catch (ParseException e) {
            return input;
        }
        SimpleDateFormat dayOfMonth = new SimpleDateFormat("d");
        int day = Integer.parseInt(dayOfMonth.format(dateTime));
        String dayWithSuffix = day + SUFFIXES[day];
        String formattedDateTime = outputFormat.format(dateTime);
        output = dayWithSuffix + " of " + formattedDateTime;
        return output;
    }
}
