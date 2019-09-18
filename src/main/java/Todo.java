/**
 * Represents Todo object.
 */
public class Todo extends Task {

    /**
     * Constructor of ToDo.
     * @param description task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string representation of ToDo object.
     * @return string representation of ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static Todo outputAsTodo (String lineToRead) {
        return new Todo(lineToRead);
    }

    /**
     * Returns description of Todo task.
     * @return description.
     */
    public String getDescription() {
        return super.getDescription();
    }
}
