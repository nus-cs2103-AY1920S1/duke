import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected boolean isDone = false;
    protected String description;
    protected static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("EEE, d MMM uuuu, hh.mma");
    
    public Task(String description) {
        this.description = description;
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
    public String getStatusIcon() {
        return this.isDone ? "✓" : "✘";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
