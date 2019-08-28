package duke.todo;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string of the currently task with formatting,
     * intended for saving usage.
     *
     * @return Formatted string of this task.
     */
    @Override
    public String getFormattedTask() {
        return "T | " + super.getDescription();
    }

    /**
     * Returns a string of this task for display usage.
     *
     * @return Formatted string of this task for display.
     */
    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + this.getDescription();
    }
}
