package duke.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTime {
    /**
     * Parses date and time.
     * @param input User input representing a date and time.
     * @return
     */
    public static String parseDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return (dateTime.format(outputFormat));
    }
}
