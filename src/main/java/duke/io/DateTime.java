package duke.io;

import duke.exception.IllegalDateException;

import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class DateTime implements Comparable<DateTime> {
    private LocalDateTime dateTime;

    private static final DateTimeFormatter inputFormatter
            = DateTimeFormatter.ofPattern("d/M/y HH:mm");

    private static final DateTimeFormatter outputFormatter
            = DateTimeFormatter.ofPattern("d LLL y hh:mm a");

    public static final String EXPECTED_FORMAT_WARNING
            = "Invalid! The expected format is \"dd/mm/yyyy hh:mm\" where \"HH:mm is in 24-hrs\"";

    private DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns a new DateTime object with the given date.
     *
     * @param date the date input by the user
     * @return a new DateTime object with the given date
     */
    public static DateTime ofDate(String date) throws IllegalDateException {
        try {
            LocalDateTime ldt = LocalDateTime.parse(date, inputFormatter);
            return new DateTime(ldt);
        } catch (DateTimeParseException e) {
            throw new IllegalDateException(
                    String.format("The expected format is %s", EXPECTED_FORMAT_WARNING));
        }
    }

    /**
     * Returns a new DateTime object from a file-formatted date.
     *
     * @param date the date from a file
     * @return a new DateTime object with the given date
     */
    public static DateTime ofFileFormattedDate(String date) {
        try {
            LocalDateTime ldt = LocalDateTime.parse(date, outputFormatter);
            return new DateTime(ldt);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public int compareTo(DateTime o) {
        return this.dateTime.compareTo(o.dateTime);
    }

    @Override
    public String toString() {
        return this.dateTime.format(outputFormatter);
    }
}