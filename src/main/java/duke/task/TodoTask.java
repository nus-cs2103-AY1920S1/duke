package duke.task;

/**
 * Represents a To-do Task.
 */
public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    public TodoTask(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + this.description;
    }

    @Override
    public String store() {
        return "T|" + getStatus() + "|" + this.description;
    }
}