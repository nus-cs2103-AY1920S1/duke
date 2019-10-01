package duke.task;

/**
 * A Todo task is a Task that the user wishes to complete.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo Task.
     *
     * @param description the description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String formatToWrite() {
        if (super.isDone) {
            return String.format("T | %d | %s", 1, this.description);
        } else {
            return String.format("T | %d | %s", 0, this.description);
        }
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
