package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Inherits from abstract Task class.
 * Represents a Duke <code>event</code>
 */
public class Event extends Task {
    private LocalDateTime date1;
    private LocalDateTime date2;

    /**
     * Default constructor for Event class.
     *
     * @param taskName A String which represents the taskName
     *                 e.g., <code>event borrow books</code>
     *                 where the taskName is "borrow books"
     * @param at       A String represents two date-times
     *                 in the format <code>DD/MM/YYYY HHmm - DD/MM/YYYY HHmm</code>
     */
    public Event(String taskName, String at) throws DateTimeParseException {
        super(taskName);
        this.taskType = TypeOfTask.EVENT;
        String[] parts = at.split(" - ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.date1 = LocalDateTime.parse(parts[0], formatter);
        this.date2 = LocalDateTime.parse(parts[1], formatter);
        this.details = "(at: " + at + ")";
    }

    /**
     * Alternative constructor for Event class.
     *
     * @param taskName    A String which represents the taskName
     *                    e.g., <code>event borrow books</code>
     *                    where the taskName is "borrow books"
     * @param at          A String represents two date-times
     *                    in the format <code>DD/MM/YYYY HHmm - DD/MM/YYYY HHmm</code>
     * @param isCompleted A boolean indicating if the task is completed
     */
    public Event(String taskName, String at, boolean isCompleted) throws DateTimeParseException {
        super(taskName, isCompleted);
        this.taskType = TypeOfTask.EVENT;
        String[] parts = at.split(" - ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.date1 = LocalDateTime.parse(parts[0], formatter);
        this.date2 = LocalDateTime.parse(parts[1], formatter);
        this.details = "(at: " + at + ")";
    }


}
