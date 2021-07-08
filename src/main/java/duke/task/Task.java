package duke.task;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected boolean isDone = false;
    protected String description;
    protected static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("EEE, d MMM uuuu, hh.mma");
    
    public Task(String description) {
        this(description, false);
    }

    /**
     * Constructs a Task object.
     * @param description Description of task.
     * @param isDone Whether a task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets task status.
     * @return Task status.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns task description.
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     * @return Unicode character representing status of task.
     */
    private String getStatusIcon() {
        return this.isDone ? "✓" : "✘";
    }

    /**
     * Returns a String representing the task, of format "[✘] task".
     * @return String representing the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
