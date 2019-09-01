package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Formatter class, to handle various formatting in Duke application.
 */
public class Formatter {
    private static final DateTimeFormatter prettyFormat = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
    private static final DateTimeFormatter standardFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Converts the given datetime to human readable format.
     *
     * @param dateTime Datetime to be converted.
     * @return the given datetime in human readable format.
     */
    public static String prettyFormatDateTime(LocalDateTime dateTime) {
        return dateTime.format(prettyFormat);
    }

    /**
     * Converts the given datetime to standard format for disk storage.
     *
     * @param dateTime Datetime to be converted.
     * @return the given datetime in standard format for disk storage.
     */
    public static String standardFormatDateTime(LocalDateTime dateTime) {
        return dateTime.format(standardFormat);
    }
}
