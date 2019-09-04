/**
 * Represents a task with a description and state.
 * A <code>Task</code> object corresponds to a task represented by
 * a String and a boolean e.g., <code>"read book",true</code>
 */
public abstract class Task {
    /** Description of task. */
    protected String description;
    /** State of task. */
    protected boolean isDone;

    /**
     * Constructs a <code>Task</code> with a task description and
     * initial state being false.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns symbolic status of specified task.
     *
     * @return Symbolic status.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns condensed description of specified task.
     *
     * @return Condensed description.
     */
    public abstract String formattedString();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
