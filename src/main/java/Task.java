import java.io.Serializable;

/**
 * Represents the task given by the user.
 */
abstract class Task implements Serializable {
    String taskDescription;
    boolean isDone;

    Task(String taskDescription) {
        this.taskDescription = taskDescription;
        isDone = false;
    }

    /**
     * Marks this task as complete.
     */
    void markAsDone() {
        isDone = true;
    }

    boolean isTimed() {
        return false;
    }

    String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Formats the task such that it can be outputted in a readable form for the user.
     *
     * @return The description of the task.
     */
    @Override
    public abstract String toString();

}
