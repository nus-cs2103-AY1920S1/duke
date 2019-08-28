package util;

import error.task.UnknownDateTimeException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/***
 * Utility class to handle date and time.
 */
public class DukeDateTime {
    /***
     * Parses a string into a LocalDateTime object. E.g. 24/02/2022 0315.
     * @param dateTimeString string to be parsed, must be in the form dd/mm/yyyy HHmm.
     * @return
     * @throws UnknownDateTimeException
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws UnknownDateTimeException {
        try {
            String[] dateTimeStrings = dateTimeString.split("\\s+");

            if (dateTimeStrings.length != 2) {
                throw new UnknownDateTimeException();
            }

            String date = dateTimeStrings[0];
            String time = dateTimeStrings[1];

            String[] dayMonthYear = date.split("/");
            if (dayMonthYear.length != 3) {
                throw new UnknownDateTimeException();
            }

            int day = Integer.parseInt(dayMonthYear[0]);
            int month = Integer.parseInt(dayMonthYear[1]);
            int year = Integer.parseInt(dayMonthYear[2]);

            if (time.length() != 4) {
                throw new UnknownDateTimeException();
            }

            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(2, 4));

            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (NumberFormatException | DateTimeException e) {
            throw new UnknownDateTimeException();
        }
    }

    /***
     * Formats LocalDateTime object into string.
     * @param dateTime LocalDateTime to be formatted.
     * @return formatted string.
     */
    public static String getString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, E, HH:mma");
        return formatter.format(dateTime);
    }
}
