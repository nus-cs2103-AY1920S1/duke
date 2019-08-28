package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        this(description, false);
    }

    /**
     * Constructs a To do object.
     * @param description Description of to do.
     * @param isDone Whether a to do is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a String representing the task, of format "[T][✘] task".
     * @return String representing the task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
