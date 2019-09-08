package duke.parser;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * DateFormatter class follows a singleton pattern.
 * The instance sole job is to parse strings to LocalDate
 */
public class DateHandler {
    /** format to parse String to LocalDateTime. */
    private DateTimeFormatter dateTimeFormatter;
    /** reference to single instance, to ensure that only one instance exists. */
    private static DateHandler instance = null;

    /**
     * Private Constructor.
     * Encapsulated constructor to prevent creating more than one instance.
     */
    private DateHandler() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    }

    /**
     * Gets the single instance, if it doesn't exist, construct one.
     * @return DateFormatter object.
     */
    public static DateHandler getInstance() {
        if (instance == null) {
            instance = new DateHandler();
        }
        return instance;
    }

    /**
     * Gets a LocalDateTime object from the given string.
     * @param stringDate Date in string form "d/M/yyyy HHmm".
     * @return LocalDateTime object.
     * @throws DateTimeParseException If format is wrong.
     */
    public LocalDateTime parse(String stringDate) throws DateTimeParseException {
        return LocalDateTime.parse(stringDate, dateTimeFormatter);
    }

    /**
     * Gets the date postfix.
     *
     * @param localDateTime LocalDateTime Object.
     * @return date postfix.
     */
    private String getDatePostFix(LocalDateTime localDateTime) {
        int day = localDateTime.getDayOfMonth();
        int lastDigit = day % 10;
        switch (lastDigit) {
        case 1:
            return "st";
        case 2:
            return "nd";
        case 3:
            return "rd";
        default:
            return "th";
        }
    }

    /**
     * Gets the 12 hour formatted string from a LocalDateTime object.
     * @param localDateTime LocalDateTime that supplies the 24 hour time.
     * @return String 12 hour formatted string.
     */
    private String getTwelveHourClock(LocalDateTime localDateTime) {
        boolean isMorning = localDateTime.getHour() < 12;
        String postFix = isMorning ? "am" : "pm";
        int hour = isMorning ? localDateTime.getHour() : localDateTime.getHour() - 12;
        int min = localDateTime.getMinute();
        return String.format("%d:%02d%s", hour, min, postFix);
    }

    /**
     * Formats LocalDateTime to String in the format (d of m y, h:mmam/pm).
     *
     * @param dateTime LocalDateTime to be formatted.
     * @return String of LocalDateTime.
     */
    public String format(LocalDateTime dateTime) {
        return String.format("%s%s of %s %s, %s",
                dateTime.getDayOfMonth(),
                getDatePostFix(dateTime),
                dateTime.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                dateTime.getYear(),
                getTwelveHourClock(dateTime));
    }
}