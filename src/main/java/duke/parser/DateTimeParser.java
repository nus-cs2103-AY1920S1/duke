package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deals with date time parsing and formatting.
 */
public class DateTimeParser {
    public static DateTimeFormatter inputDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter inputTimeFormatter = DateTimeFormatter.ofPattern("HHmm");
    public static DateTimeFormatter outputDateFormatter = DateTimeFormatter.ofPattern("EEE, d MMMM yyyy");
    public static DateTimeFormatter outputDateFormatterShort = DateTimeFormatter.ofPattern("d MMMM yyyy");
    public static DateTimeFormatter outputTimeFormatter = DateTimeFormatter.ofPattern("hh:mma");

    public static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, inputDateFormatter);
    }

    public static LocalTime parseTime(String timeString) {
        return LocalTime.parse(timeString, inputTimeFormatter);
    }
}
