package duke.task;

/**
 * A Todo is a basic Task that simply describes what needs to be done.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task with the given description.
     *
     * @param description Task to be completed.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a new Todo task with the given description and status.
     *
     * @param description Task to be completed.
     * @param isDone Whether the Task has been completed or not.
     * @param priority Level of priority for this Task.
     */
    public Todo(String description, boolean isDone, Priority priority) {
        super(description, isDone, priority);
    }

    /**
     * Returns the letter "T", representing the type Todo.
     *
     * @return String of the letter "T".
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Returns a string containing the type of Task, done status, and
     * description.
     *
     * @return String describing the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
