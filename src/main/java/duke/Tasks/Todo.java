package duke.tasks;

/**
 * Represents a TodoTask which extends the Task Class
 * a TodoTask object is represented by a String description.
 */

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
