package duke.task;

/**
 * Represents an actual to-do task that is to be done by the User.
 */
public class Todo extends Task {

    /**
     * Class constructor that specifies description of the
     * To-do task
     *
     * @param description Description of To-do.
     */
    public Todo(String description) {
        super(description);
        Task.totalTasks++;
    }

    /**
     * Class constructor that specifies description of the
     * To-do task and completion of status
     *
     * @param description Description of To-do.
     * @param done Completion status of To-do
     */
    public Todo(String description, int done) {
        super(description, done);
        Task.totalTasks++;
    }

    /**
     * Returns string representation of current To-do, indicating
     * completion status.
     *
     * @return String representation of To-do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
