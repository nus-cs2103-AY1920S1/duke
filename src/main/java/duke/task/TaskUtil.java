package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.command.DukeInvalidArgumentException;

/**
 * Class providing several utility methods for parsing and validating task data.
 */
public abstract class TaskUtil {
    /** The required input date format to use. */
    private static final String DATE_FORMAT = "d/M/y HHmm";
    /** The required input time format to use. */
    private static final String TIME_FORMAT = "HHmm";
    /** The output format for displaying dates and times. */
    private static final String DISPLAY_FORMAT = "EEE, d MMM y h:mma";
    /** The dateTime formatter that uses the DATE_FORMAT pattern. */
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    /** The dateTime formatter that uses the TIME_FORMAT pattern. */
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
    /** The dateTime formatter that uses the DISPLAY_FORMAT pattern. */
    private static final DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern(DISPLAY_FORMAT);

    /**
     * Utility method for validating the input task description.
     *
     * @param description The input description string.
     * @throws DukeInvalidArgumentException If the description is empty.
     */
    public static void validateTaskDescription(String description)
            throws DukeInvalidArgumentException {

        if (description.length() == 0) {
            throw new DukeInvalidArgumentException(
                    "User specified description of task is empty",
                    " \u2639 OOPS!!! The description of a task cannot be empty.");
        }
    }

    /**
     * Method for parsing the input string using the dateFormatter.
     * Returns the LocalDateTime representation of the string.
     *
     * @param dateString The input string.
     * @return The localDateTime object.
     * @throws DukeInvalidArgumentException If the input string format does not follow the pattern.
     */
    static LocalDateTime getDateFromString(String dateString) throws DukeInvalidArgumentException {
        try {
            return LocalDateTime.parse(dateString.trim(), dateFormatter);
        } catch (DateTimeParseException ex) {
            throw new DukeInvalidArgumentException(
                    "Invalid date format inputted by user",
                    " \u2639 OOPS!!! The format of the timing is invalid!\n"
                            + " Enter your time with " + DATE_FORMAT);
        }
    }

    /**
     * Method for parsing the input string using the timeFormatter.
     * Returns the LocalTime representation of the string.
     *
     * @param timeString The input string.
     * @return The LocalTime object.
     * @throws DukeInvalidArgumentException If the input string format does not follow the pattern.
     */
    static LocalTime getTimeFromString(String timeString) throws DukeInvalidArgumentException {
        try {
            return LocalTime.parse(timeString.trim(), timeFormatter);
        } catch (DateTimeParseException ex) {
            throw new DukeInvalidArgumentException(
                    "Invalid time format inputted by user",
                    " \u2639 OOPS!!! The format of the timing is invalid!\n"
                            + " Enter your time with " + TIME_FORMAT);
        }
    }

    /**
     * Retrieves the string representation of the LocalDateTime object.
     * Uses the displayFormatter and DISPLAY_FORMAT to format the string.
     *
     * @param dateTime The input LocalDateTime object.
     * @return The string representation of the localDateTime.
     */
    static String getDisplayTime(LocalDateTime dateTime) {
        return displayFormatter.format(dateTime);
    }
}
