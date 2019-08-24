/**
 * Encapsulates a Task object of the type Todo.
 * Represents a Todo task that has NO due date/ time imposed.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object.
     *
     * @param description This is the short description of the task.
     */
    public Todo(String description) {
        super(description);
        this.typeOfTask = "T";
    }
}
