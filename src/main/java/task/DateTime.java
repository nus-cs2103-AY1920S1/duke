package task;

import main.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    private LocalDateTime dateTime;

    DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public DateTime(String rawDateTimeFormat) throws DukeException {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            this.dateTime = LocalDateTime.parse(rawDateTimeFormat, formatter);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Invalid date-time format. Date-time should be in dd/MM/yyyy HHmm format.");
        }

    }

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
