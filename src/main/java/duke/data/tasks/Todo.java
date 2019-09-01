package duke.data.tasks;

/**
 * Implements a todo task.
 * @author Lim Yong Shen, Kevin
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
     * Returns the command used to create this todo task.
     * @return The command used to create this todo task.
     */
    @Override
    public String getCommandString() {
        return String.format("todo %s", description);
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
