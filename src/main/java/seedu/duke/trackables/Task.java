package seedu.duke.trackables;

/**
 * Represents a plain old Task that contains a single description and its done state.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task using string arguments. Typically used when restoring Task from Storage.
     *
     * @param args String arguments containing Event data from Storage.
     */
    public Task(String... args) {
        this.isDone = !args[1].equals("0");
        this.description = args[2];
    }

    private String getStatusIcon() {
        //return tick or X symbols
        return (this.isDone ? "\u2713" : "\u2718");
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Gets the Task in its String equivalent form.
     *
     * @return
     */
    public String getAsString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(isDone ? "1" : "0").append(" | ").append(this.description);
        return sb.toString();
    }
}