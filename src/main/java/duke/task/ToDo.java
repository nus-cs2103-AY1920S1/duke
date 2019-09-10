package duke.task;

/**
 * To do tasks which extends from the Task class. Keeps track of tasks
 * with addition of the description of the task.
 */
public class ToDo extends Task {
    /**
     * Constructor for the To Do class. Takes note of the description of
     * the tasks.
     *
     * @param description Gives the main outline of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Format the to do task into a string.
     *
     * @return the to do task formatted as a string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
