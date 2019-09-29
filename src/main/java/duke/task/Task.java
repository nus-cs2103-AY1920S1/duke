package duke.task;

import java.time.format.DateTimeFormatter;


/**
 * Task is the base base class for all tasks. Basically, a Task object encapsulates the information about the
 * description and status (done or not).
 */
public class Task {

    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy, HH:mm");
    protected String description;
    protected boolean isDone;

    /**
     * This is a class constructor specifying the description for a task. The <code>isDone</code> status is set to be
     * <code>false</code>.
     *
     * @param description a string containing the detail for this task
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This is a class constructor specifying the description for a task and whether the task is done.
     *
     * @param description a string containing the detail for this task
     * @param isDone      a boolean indicating whether this task is done
     */
    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "✓" : "✘");
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    /**
     * Returns a string representation of a task.
     *
     * @return a string representation of a task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
