package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import duke.task.Task;

/**
 * A child class of Task class that represents a deadline Task
 * which has a description and time.
 */
public class Deadline extends Task {

    protected String time;
    protected Date date;

    /**
     * Constructs a Deadline task object with specified desciption and time.
     * @param description Description that describes the task.
     * @param time Time by which the task needs to be done.
     * @throws ParseException if time format does not follow the format required for parsing
     the time when passed in the parse method.
     */
    public Deadline(String description, String time) throws ParseException {
        super(description);
        this.time = time;
        super.typeOfTask = "D";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hhmm");
        this.date = formatter.parse(time);
    }

    /**
     * Gets the time by which the task has to be done.
     * @return The time by which the task has to be done.
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Gets the description of the deadline task.
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the date of the deadline task.
     * @return The date of the deadline task.
     */
    public Date getDate() {
        return this.date;
    }

    public String toString() {
        return "[" + this.typeOfTask + "]" + "[" + this.getStatusIcon() + "] "
                + this.description + " (by: " + time + ")";
    }
}
