package duke.todo;

public class Todo extends Task {

    private String duration;

    /**
     * Public constructor for todo tasks without fixed duration.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
        this.duration = null;
    }

    /**
     * Private constructor for todo tasks with fixed duration.
     *
     * @param description Task description.
     * @param duration Duration of the task.
     */
    private Todo(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Factory method to generate Todo with fixed duration.
     *
     * @param description Task description.
     * @param duration Duration of the task.
     * @return Task with fixed duration.
     */
    public static Todo getTodoWithDuration(String description, String duration) {
        return new Todo(description, duration);
    }

    /**
     * Returns a string of the currently task with formatting,
     * intended for saving usage.
     *
     * @return Formatted string of this task.
     */
    @Override
    public String getFormattedTask() {
        return "T |" + super.getDescription();
    }

    /**
     * Returns duration of this task and null if there isn't a fix duration.
     *
     * @return Duration of this task.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Returns true if this task has a fixed duration and false otherwise.
     *
     * @return True if this task has a fixed duration and false otherwise.
     */
    public boolean hasFixedDuration() {
        return duration != null;
    }

    /**
     * Returns a string of this task for display usage.
     *
     * @return Formatted string of this task for display.
     */
    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + this.getDescription()
                + (hasFixedDuration() ? " (" + duration + ")" : "");
    }
}
