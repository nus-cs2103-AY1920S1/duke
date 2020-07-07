package duke.task;

import java.lang.reflect.Array;

/**
 * Represents a deadline, which is a specific type of task.
 */
public class Deadline extends Task {
    protected Date byDate;
    protected Time byTime;
    protected String exactBy;

    public Deadline(String description, String by) {
        super(description);
        this.exactBy = by;
        String[] temp = by.split(" ");
            byDate = new Date((String) Array.get(temp, 0));
            byTime = new Time((String) Array.get(temp, 1));
    }

    /**
     * Returns the deadline time formatted exactly as keyed in by the user.
     *
     * @return deadline time formatted exactly as keyed in by user.
     */
    public String getExactBy() {
        return this.exactBy;
    }

    /**
     * Returns a string representing the type of task.
     *
     * @return string representing type of task.
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns string description of the task.
     *
     * @return string description of task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDate + ", " + this.byTime +")";
    }
}
