package duke.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * Represents a date and time object.
 */
public class DateTime {
    /**
     * Parses date and time and. Uses a specific formatting: dd/MM/yyyy HHmm.
     * Returns the date and time in the format: Day Month Year hour:minute:second AM/PM.
     * @param input User input representing a date and time.
     * @return a String representing the date and time.
     */
    public static String parseDateTime(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return (dateTime.format(outputFormat));
    }
}
