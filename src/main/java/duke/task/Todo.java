package duke.task;

/**
 * Handles a certain type of Task with only a description.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toSave() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
