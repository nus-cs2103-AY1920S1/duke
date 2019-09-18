package duke.task;

/**
 * Handles a certain type of Task with only a description.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, int frequency) {
        super(description, frequency);
    }

    @Override
    public String toSave() {
        return "T | " + (isDone ? 1 : 0) + " | " + description + " | " + frequency;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
