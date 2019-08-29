package task;

import main.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * DateTime object to make sense of date and time.
 */
public class DateTime {
    private LocalDateTime dateTime;

    /**
     * Constructs a DateTime object
     *
     * @param dateTime in LocalDateTime format.
     */
    DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Constructs a DateTime object
     *
     * @param rawDateTimeFormat A String with date and time information, with format dd/MM/yyyy HHmm,
     *                          e.g. 25/07/2019 2330
     * @throws DukeException If format is not recognized.
     */
    public DateTime(String rawDateTimeFormat) throws DukeException {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            this.dateTime = LocalDateTime.parse(rawDateTimeFormat, formatter);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Invalid date-time format. Date-time should be in dd/MM/yyyy HHmm format.");
        }

    }

    /**
     * Makes sense of the task list date time format.
     *
     * @param taskListDTformat A string with date and time information, with format LLLL dd yyyy hh:mm a,
     *                         e.g. Jul 25 2019 11:30pm
     * @return DateTime object which stores the date and time.
     * @throws DukeException If format is not recognized.
     */
    public static DateTime parseTaskListFormat(String taskListDTformat) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLLL dd yyyy hh:mm a");
        LocalDateTime resultDateTime;
        try {
            resultDateTime = LocalDateTime.parse(taskListDTformat, formatter);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Invalid date-time format. Date-time should be in dd/MM/yyyy HHmm format.");
        }
        return new DateTime(resultDateTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLLL dd yyyy hh:mm a");
        return dateTime.format(formatter);
    }

}
