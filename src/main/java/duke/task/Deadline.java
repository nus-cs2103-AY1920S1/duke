package duke.task;

/**
 * A Task that contains both a description and a date which the task must be completed by.
 */
public class Deadline extends Task {
    protected String ddl;

    /**
     * Constructs an deadline task.
     *
     * @param description the description of the deadline task.
     * @param d the specific deadline of that task.
     */
    public Deadline(String description, String d) {
        super(description);
        this.ddl = d;
    }

    /**
     * Informs the deadline of a particular task.
     *
     * @return deadline of the task.
     */
    public String getDdl() {
        return ddl;
    }

    /**
     * Provides the string representation of an instance of deadline task.
     *
     * @return the deadline task's string representation.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), getDescription(), getDdl());
    }
}
