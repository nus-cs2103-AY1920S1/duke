package duke.task;

/**
 * Represents a basic task to be done by user
 */

public class Todo extends Task {
    public Todo(String name, boolean isDone, String type) {
        super(name, isDone, type);
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getName();
    }
}
