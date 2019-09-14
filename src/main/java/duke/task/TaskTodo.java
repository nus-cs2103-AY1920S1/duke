package duke.task;

/**
 * The TaskTodo class provides a basic implementation of the abstract base class Task.
 */
public class TaskTodo extends Task {
    private TaskTodo(String description) {
        super(description, null);
        this.type = "T";
    }

    private TaskTodo(String description, boolean isDone) {
        super(description, isDone, null);
        this.type = "T";
    }

    /**
     * Returns a TaskTodo object with the given description.
     *
     * @param description of the task
     * @return a new TaskTodo object.
     */
    public static TaskTodo of(String description) {
        return new TaskTodo(description);
    }

    /**
     * Returns a TaskTodo object with the given description and truth condition of isDone.
     *
     * @param description of task
     * @param isDone truth condition of the done status of the task
     * @return a new TaskTodo object
     */
    public static TaskTodo of(String description, boolean isDone) {
        return new TaskTodo(description, isDone);
    }

    /**
     * Returns a TaskTodo object from its file-formatted-form representation.
     * The expected format is "T | [1|0] | description |".
     *
     * @param fileFormattedForm of a TaskTodo object
     * @return a new TaskTodo object
     */
    public static TaskTodo ofFileFormattedForm(String fileFormattedForm) {
        String[] elements = fileFormattedForm.split("\\s+\\|\\s+");
        String desc = elements[2];
        boolean done = elements[1].equals(IS_DONE);
        return TaskTodo.of(desc, done);
    }

    /**
     * Returns the String representation of this Task, for writing to file.
     * 
     * @return the String representation of this Task suitable for writing to file
     */
    public String toFileFormattedString() {
        String status = getStatusIcon().equals(IS_DONE) ? IS_DONE : IS_NOT_DONE;
        return String.format("%s | %s | %s", type, status, description);
    }

    /**
     * Returns the String representation of this Task.
     *
     * @return the String representation of this Task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type, getStatusIcon(), description);
    }
}
