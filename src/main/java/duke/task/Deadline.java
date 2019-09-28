package duke.task;

/**
 * A Task that contains both a description and a date which the task must be completed by.
 */
public class Deadline extends TimeLimitTask {

    /**
     * Constructs a deadline task with time limit.
     *
     * @param description the description of the deadline task.
     * @param d the specific deadline of that task.
     */
    public Deadline(String description, String d) {
        super(description, d);
    }

    /**
     * Informs the deadline of a particular task.
     *
     * @return deadline of the task.
     */
    public String getDdl() {
        return getDateTime();
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
