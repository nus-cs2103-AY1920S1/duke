package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Inherits from abstract Task class.
 * Represents a Duke <code>deadline</code>
 */
public class Deadline extends Task {
    protected Date byDate;

    /**
     * Default constructor for Deadline class
     *
     * @param taskName A String which represents the taskName
     *                 e.g., <code>deadline borrow books</code>
     *                 where the taskName is "borrow books"
     * @param by       A String for date-time in the format <code>DD/MM/YYYY HHmm</code>
     */
    public Deadline(String taskName, String by) throws ParseException {
        super(taskName);
        this.taskType = TypeOfTask.DEADLINE;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.byDate = formatter.parse(by);
        this.details = "(by: " + by + ")";
    }

    /**
     * Alternative constructor for Deadline class
     *
     * @param taskName    A String which represents the taskName
     *                    e.g., <code>deadline borrow books</code>
     *                    where the taskName is "borrow books"
     * @param by          A String for date-time in the format <code>DD/MM/YYYY HHmm</code>
     * @param isCompleted A boolean indicating if the task is completed
     */
    public Deadline(String taskName, String by, boolean isCompleted) throws ParseException {
        super(taskName, isCompleted);
        this.taskType = TypeOfTask.DEADLINE;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.byDate = formatter.parse(by);
        this.details = "(by: " + by;
    }


}
