package duke.task;

/**
 * Represents a to-do task.
 */
public class Todo extends Task {
    /**
     * Class constructor specifying the task to be done.
     *
     * @param desc The description of the to-do task.
     */
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] " + super.toString();
    }
}
