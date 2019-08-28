import java.io.Serializable;

/**
 * Represents the task given by the user.
 */
public abstract class Task implements Serializable {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String s) {
        taskDescription = s;
        isDone = false;
    }

    /**
     * Marks this task as complete.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Formats the task such that it can be outputted in a readable form for the user.
     * @return The description of the task.
     */
    @Override
    public abstract String toString();
}
