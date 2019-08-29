package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Inherits from abstract Task class.
 * Represents a Duke <code>event</code>
 */
public class Event extends Task {
    Date date1;
    Date date2;

    /**
     * Default constructor for Event class
     *
     * @param taskName A String which represents the taskName
     *                 e.g., <code>event borrow books</code>
     *                 where the taskName is "borrow books"
     * @param at       A String represents two date-times
     *                 in the format <code>DD/MM/YYYY HHmm - DD/MM/YYYY HHmm</code>
     */
    public Event(String taskName, String at) throws ParseException {
        super(taskName);
        this.taskType = TypeOfTask.EVENT;
        String[] parts = at.split(" - ");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.date1 = formatter.parse(parts[0]);
        this.date2 = formatter.parse(parts[1]);
        this.details = "(at: " + at + ")";
    }

    /**
     * Alternative constructor for Event class
     *
     * @param taskName    A String which represents the taskName
     *                    e.g., <code>event borrow books</code>
     *                    where the taskName is "borrow books"
     * @param at          A String represents two date-times
     *                    in the format <code>DD/MM/YYYY HHmm - DD/MM/YYYY HHmm</code>
     * @param isCompleted A boolean indicating if the task is completed
     */
    public Event(String taskName, String at, boolean isCompleted) throws ParseException {
        super(taskName, isCompleted);
        this.taskType = TypeOfTask.EVENT;
        String[] parts = at.split(" - ");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.date1 = formatter.parse(parts[0]);
        this.date2 = formatter.parse(parts[1]);
        this.details = "(at: " + at;

    }


}
