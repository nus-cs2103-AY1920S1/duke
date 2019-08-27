package duke;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DateTime {
    private static String[] suffixes = { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th", "th", "th", "th",
        "th", "th", "th", "th", "th", "th", "th", "th", "st", "nd", "rd", "th", "th", "th", "th", "th",
        "th", "th", "st" };

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
        SimpleDateFormat dayOfMonth = new SimpleDateFormat("d");
        int day = Integer.parseInt(dayOfMonth.format(dateTime));
        String dayWithSuffix = day + suffixes[day];
        output = outputFormat.format(dateTime);
        return (dayWithSuffix + " of " + output);
    }
}
