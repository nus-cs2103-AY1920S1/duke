package duke.task;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a deadline task.
 * Has functions to print the task information to a standard form,
 * to set and get the given time of the deadline.
 */
public class Deadline extends Task {
    private Date givenTime;
    private SimpleDateFormat df;

    public Deadline(char taskType, String taskDescription, boolean isDone, String givenTime) {
        super(taskType, taskDescription, isDone);

        try {
            df = new SimpleDateFormat("dd/mm/yyyy Hm");
            this.givenTime = df.parse(givenTime, new ParsePosition(0));

        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

    public Deadline(String taskDescription, boolean isDone, String givenTime) {
        super(taskDescription, isDone);

        try {
            df = new SimpleDateFormat("dd/mm/yyyy Hm");
            this.givenTime = df.parse(givenTime, new ParsePosition(0));

        } catch (NullPointerException n) {
            throw new NullPointerException();
        }
    }

    /**
     * Returns string representation of task,
     * in terms of initial, icon of done, task description and given deadline.
     *
     * @return String representation of task.
     */
    @Override
    public String printTask() {
        return "[" + getFirstCharTask() + "][" + getIcon() + "] " + getTaskDescription() + " (by: " + getGivenTime() + ")";
    }

    /**
     * Set time for given deadline,
     *
     * @param time String representation of time.
     * @throws ParseException If format of time in string does not match specified format.
     */
    public void setGivenTime(String time) throws ParseException {
        this.givenTime = df.parse(time);
    }

    /**
     * Get time for given deadline,
     */
    public String getGivenTime() {
        return df.format(givenTime);
    }
}
