package duke;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Encapsulates date & time.
 */
public class DateTime {
    private static String[] suffixes = { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th", "th", "th",
            "th", "th", "th", "th", "th", "th", "th", "th", "th", "st", "nd", "rd", "th", "th", "th", "th", "th",
            "th", "th", "st" };

    /**
     * Parses date and time.
     *
     * @param input  Date and time to be parsed.
     * @return Date and time in the indicated output format if input format is a match, and original input otherwise.
     */
    public static String parse(String input) {
        Date dateTime = null;
        String output = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat("d/MM/yyyy HHmm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMMMM yyyy, h:mm a");
        try {
            dateTime = inputFormat.parse(input);
        } catch (ParseException e) {
            return input;
        }
        SimpleDateFormat DayOfMonth = new SimpleDateFormat("d");
        int day = Integer.parseInt(DayOfMonth.format(dateTime));
        String dayWithSuffix = day + suffixes[day];
        output = outputFormat.format(dateTime);
        return (dayWithSuffix + " of " + output);
    }
}
