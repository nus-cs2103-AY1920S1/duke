import java.util.Date;

public class Deadline extends Task {

    protected Date by;

    /**
     * Represents the Deadline tasks in the task list.
     * @param description refers to the task details
     * @param by refers to the date by which the task should be done
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the date of the Deadline task.
     * @return Date by
     */
    public Date getBy() {
        return by;
    }

    /**
     * Returns the stringified form of the task.
     * @return String task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}