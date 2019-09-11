package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Inherits from abstract Task class.
 * Represents a Duke <code>deadline</code>
 */
public class Deadline extends Task {

    private LocalDateTime byDate;


    /**
     * Default constructor for Deadline class.
     *
     * @param taskName A String which represents the taskName
     *                 e.g., <code>deadline borrow books</code>
     *                 where the taskName is "borrow books"
     * @param by       A String for date-time in the format <code>DD/MM/YYYY HHmm</code>
     */
    public Deadline(String taskName, String by) throws DateTimeParseException {
        super(taskName);
        this.taskType = TypeOfTask.DEADLINE;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.byDate = LocalDateTime.parse(by, formatter);
        this.details = "(by: " + by + ")";
    }

    /**
     * Alternative constructor for Deadline class.
     *
     * @param taskName    A String which represents the taskName
     *                    e.g., <code>deadline borrow books</code>
     *                    where the taskName is "borrow books"
     * @param by          A String for date-time in the format <code>DD/MM/YYYY HHmm</code>
     * @param isCompleted A boolean indicating if the task is completed
     */
    public Deadline(String taskName, String by, boolean isCompleted) throws DateTimeParseException {
        super(taskName, isCompleted);
        this.taskType = TypeOfTask.DEADLINE;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.byDate = LocalDateTime.parse(by, formatter);
        this.details = "(by: " + by + ")";
    }
}
