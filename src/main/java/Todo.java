/**
 * Represents a To-do Task
 */
public class Todo extends Task {

    Todo(String description) {
        super(description);
    }

    Todo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + this.description;
    }

    @Override
    String store(){
        return "T|" + getStatus() + "|" + this.description;
    }
}