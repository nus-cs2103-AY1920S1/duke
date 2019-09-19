package duke.task;

/**
 * Encapsulates a task object of type Deadline.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructs a Deadline object.
     *
     * @param title the title of the deadline.
     * @param deadline its deadline.
     */
    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
        this.type = "D";
    }

    /**
     * Returns the string representation of the Deadline object.
     * It takes the form of [type][done status][date][deadline].
     *
     * @return string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", type, status, title, deadline);
    }

    /**
     * Returns the data summary of this task to record this Deadline object in the database.
     *
     * @return the data summary of this Deadline object.
     */
    public String getSummaryForDatabase() {
        int status = isDone ? 1 : 0;
        return String.format("%s | %d | %s | %s", type, status, title, deadline);
    }
}
