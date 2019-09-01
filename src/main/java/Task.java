import java.time.format.DateTimeFormatter;

/**
 * Represents a task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Constructor for Task.
     * @param description String representation of the description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task which takes in a String description and whether it is done or not.
     * @param description String representation of the description
     * @param isDone whether it is done or not
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the String description of the task.
     * @return the String description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the boolean value whether task is done or not.
     * @return boolean on task is done or not
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Sets Task to be done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Converts whether task is done or not to a status.
     * + symbols means done - symbol means not done
     * @return String representation on whether task is done or not
     */
    public String getStatusIcon() {
        return (isDone ? "+" : "-");
    }

    /**
     * Gets the String representation of the task.
     * @return the String representation of the task
     */
    @Override
    public String toString() {
        String mark = "[" + getStatusIcon() + "] " + getDescription();
        return mark;
    }

    /**
     * @return String that would be written into data file
     */
    public String toDataString() {
        return isDone ? "1 | " + getDescription() : "0 | " + getDescription();
    }
}
