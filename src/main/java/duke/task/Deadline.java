package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;

public class Deadline extends Task {

    private SimpleDateFormat deadline;
    protected String by;
    private Date date;

    /**
     * Creates a task with a deadline.
     *
     * @param description description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) throws DateTimeException {
        super(description);
        this.by = by;
        this.type = "D";

        this.deadline = new SimpleDateFormat("dd/MM/yyyy HHmm");
    }

    /**
     * Returns deadline as the task type.
     *
     * @return type of task
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns deadline of the task.
     *
     * @return deadline of task
     */
    @Override
    public String getBy() {
        return this.by;
    }

    /**
     * Converts the deadline from SimpleDateFormat to String.
     *
     * @return deadline of task in the form of a string
     * @throws DateTimeException deadline is in the wrong format
     */
    public String convertEventTime() throws DateTimeException {

        try {
            date = deadline.parse(this.by);
            return deadline.format(date);
        } catch (ParseException exception) {
            System.out.println("OOPS!!! Please enter a valid deadline");
        }
        return "";
    }

    /**
     * Represents the task in a format suitable for the user to read.
     *
     * @return string representation of task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + convertEventTime() + ")";
    }
}
