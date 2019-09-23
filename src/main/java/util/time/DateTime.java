package util.time;

import error.datetime.UnknownDateTimeException;
import util.time.DayParser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class to handle date and time.
 */
public class DateTime {
    private static final String dateRegex = "((([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})(\\s([0-9]{4}))?))";
    private static final String dayRegex = "((?i)(Today|Tomorrow|Mon|Monday|Tue|Tues|Tuesday|Wed|Wednesday|Thu|Thursday|Fri|Friday|Sat|Saturday|Sun|Sunday)(\\s([0-9]{4}))?)";

    /**
     * Parses a string into a LocalDateTime object. E.g. 24/02/2022 0315.
     *
     * @param dateTimeString string to be parsed, must be in the form dd/mm/yyyy HHmm.
     * @return LocalDateTime.
     * @throws UnknownDateTimeException if date and time is not in the correct format.
     */
    public static LocalDateTime parse(String dateTimeString) throws UnknownDateTimeException {
        try {
            if (dateTimeString.matches(dateRegex)) {
                return parseAsDate(dateTimeString);
            }

            if (dateTimeString.matches(dayRegex)) {
                return parseAsDay(dateTimeString);
            }

            throw new UnknownDateTimeException();
        } catch (NumberFormatException | DateTimeException e) {
            throw new UnknownDateTimeException();
        }
    }

    private static LocalDateTime parseAsDate(String dateTimeString) throws UnknownDateTimeException {
        String[] dateTimeStrings = dateTimeString.split("\\s+");

        if (dateTimeStrings.length > 2) {
            throw new UnknownDateTimeException();
        }

        String date = dateTimeStrings[0];

        String[] dayMonthYear = date.split("/");
        if (dayMonthYear.length != 3) {
            throw new UnknownDateTimeException();
        }

        int day = Integer.parseInt(dayMonthYear[0]);
        int month = Integer.parseInt(dayMonthYear[1]);
        int year = Integer.parseInt(dayMonthYear[2]);

        String time = null;
        if (dateTimeStrings.length == 2) {
            time = dateTimeStrings[1];
        }

        if (time != null && time.length() != 4) {
            throw new UnknownDateTimeException();
        }

        if (time != null) {
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(2, 4));

            return LocalDateTime.of(year, month, day, hour, minute);
        } else {
            return LocalDateTime.of(year, month, day, 0, 0);
        }
    }

    private static LocalDateTime parseAsDay(String dateTimeString) throws UnknownDateTimeException {
        String[] dayTimeStrings = dateTimeString.split("\\s+");

        String day = dayTimeStrings[0];
        LocalDateTime dateTime = DayParser.parse(day);

        String time = null;
        if (dayTimeStrings.length == 2) {
            time = dayTimeStrings[1];
        }

        if (time != null && time.length() != 4) {
            throw new UnknownDateTimeException();
        }

        if (time != null) {
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(2, 4));

            dateTime = dateTime.withHour(hour);
            dateTime = dateTime.withMinute(minute);

            return dateTime;
        } else {
            return dateTime;
        }

    }

    /**
     * Formats LocalDateTime object into string.
     *
     * @param dateTime LocalDateTime to be formatted.
     * @return formatted string.
     */
    public static String getString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, E, HH:mma");
        return formatter.format(dateTime);
    }
}
