package duke.task;

/**
 * Represents a To-do Task
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + this.description;
    }

    @Override
    public String store(){
        return "T|" + getStatus() + "|" + this.description;
    }
}