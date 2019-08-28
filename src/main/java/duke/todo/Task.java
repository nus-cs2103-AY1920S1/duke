package duke.todo;

public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a task based on the description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon based on the status of the current
     * task.
     *
     * @return Status icon of this task.
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713] " : "[\u2718] "); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public String getFormattedTask() {
        return description;
    };

    @Override
    public String toString() {
        return description;
    }

    /**
     * Marks this task as done.
     */
    void markAsDone() {
        isDone = true;
    }
}
