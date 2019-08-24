import java.util.Objects;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return A tick symbol when this task is done or a X symbol when this task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Getter for this task's description.
     *
     * @return The description of this task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for whether this task is done.
     *
     * @return Whether this task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return isDone == task.isDone
                && description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, isDone);
    }
}
