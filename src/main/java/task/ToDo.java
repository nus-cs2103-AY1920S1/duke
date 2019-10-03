package task;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {

    /**
     * Initializes ToDo with description.
     *
     * @param description of the todo task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Initializes ToDo with description and isDone status.
     *
     * @param description description of the todo task
     * @param isDone true if todo task is completed. Else false
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts task object to format use in file save.
     * @return String that is in file save format
     */
    @Override
    public String toSaveFormat() {
        return String.format("T | %s | %s",
                this.isDone ? "Done" : "Not Done",
                this.getDescription());
    }
}