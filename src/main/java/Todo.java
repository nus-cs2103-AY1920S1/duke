/**
 * Todo is a form of task
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.type = "todo";
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "]" + description;
    }
}