package duke.task;

/**
 * Represents a normal task without a deadline or a specific time of occurrence.
 * The <code>NormalTask</code> class inherits from the <code>Task</code> class.
 */
public class NormalTask extends Task {

    /**
     * Constructs a <code>NormalTask</code> object.
     *
     * @param description A string that describes the specific
     *          content of the task.
     */
    public NormalTask(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the task to be stored in a local file.
     *
     * @return A string in a specific format for clear display in a local file.
     */
    @Override
    public String format() {
        return "T | " + this.getStatusIcon() + " | " + this.getDescription();
    }

    /**
     * Returns a string representation of the task to be printed upon user 
     * inquiry.
     *
     * @return A string representation of the task that displays the type and
     *          description of task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
