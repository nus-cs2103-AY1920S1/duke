package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.DukeInvalidArgumentException;

class TaskUtil {
    private static String DATE_FORMAT = "dd/MM/yyyy HHmm";
    private static String TIME_FORMAT = "HHmm";
    private static String DISPLAY_FORMAT = "EEE, d MMM y h:mma";
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
    private static DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern(DISPLAY_FORMAT);

    static LocalDateTime getDateFromString(String dateString) throws DukeInvalidArgumentException {
        try {
            return LocalDateTime.parse(dateString, dateFormatter);
        } catch (DateTimeParseException ex) {
            throw new DukeInvalidArgumentException(
                    "Invalid date format inputted by user",
                    " \u2639 OOPS!!! The format of the timing is invalid!\n"
                            + " Enter your time with " + DATE_FORMAT);
        }
    }

    static LocalTime getTimeFromString(String timeString) throws DukeInvalidArgumentException {
        try {
            return LocalTime.parse(timeString, timeFormatter);
        } catch (DateTimeParseException ex) {
            throw new DukeInvalidArgumentException(
                    "Invalid time format inputted by user",
                    " \u2639 OOPS!!! The format of the timing is invalid!\n"
                            + " Enter your time with " + TIME_FORMAT);
        }
    }

    static String getDisplayTime(LocalDateTime dateTime) {
        return displayFormatter.format(dateTime);
    }
}
