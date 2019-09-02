package duke.task;

/**
 * Represents a task of type todo. A <code>Todo</code> object contains a description,
 * a  boolean representing whether or not the task has been done and additional information.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo.
     * @param description description of the task
     * @param isDone whether or not the task has been done
     */
    public Todo(String description, boolean isDone, String info) {
        super(description, isDone, info);
        super.type = Type.TODO;
    }

    /**
     * Overrides toString method.
     * @return a String in the to-be-displayed format
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a String in the format for file saving.
     * @return a string in the saved format
     */
    @Override
    public String getFileStringFormat() {
        if (isDone()) {
            return "T | 1 | " + description;
        } else {
            return "T | 0 | " + description;
        }
    }
}
