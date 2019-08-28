package util;

import error.task.UnknownDateTimeException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DukeDateTime {
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

    public static String getString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, E, HH:mma");
        return formatter.format(dateTime);
    }
}
