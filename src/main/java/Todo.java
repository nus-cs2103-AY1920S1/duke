/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Initiates a Todo object.
     * @param description content of a todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initiates a Todo object.
     * @param description content of a todo task
     * @param isDone mark if the todo task is already done
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Expresses a Todo object in natural language.
     * @return string representation of a task
     */
    public String repr() {
        return "[T][" + super.getStatusIcon() + "] " + description;
    }

}
