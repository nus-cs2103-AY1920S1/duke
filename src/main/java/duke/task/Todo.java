package duke.task;

public class Todo extends Task {
    /**
     * Creates a Todo object.
     *
     * @param description The description of the task.
     * @param priority The priority of the task.
     */
    public Todo(String description, TaskPriority priority) {
        super(description, priority);
    }

    /**
     * Creates a formatted string of the Todo data for storage.
     *
     * @return A string of the formatted data.
     */
    public String storageFormat() {
        return String.format("T | %s", super.storageFormat());
    }

    /**
     * Creates a formatted string of the Todo data for display.
     *
     * @return A string of the formatted data.
     */
    public String displayFormat() {
        return "[T]" + super.displayFormat();
    }
}
