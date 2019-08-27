package duke.data.tasks;

/**
 * Implements a todo task.
 * @author lyskevin
 */
public class Todo extends Task {

    /**
     * Constructs a todo task with the specified description.
     * @param description The specified description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a todo task with the specified description and isDone status.
     * @param description The specified description.
     * @param isDone The specified isDone status.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the String representation of this todo task's type.
     * @return The String representation of this todo task's type.
     */
    public String getType() {
        return "todo";
    }

    /**
     * Returns the string representation of this todo task.
     * @return The string representation of this todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

}
