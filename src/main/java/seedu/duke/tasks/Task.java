package seedu.duke.tasks;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public abstract class Task implements Serializable {
    private static final long serialVersionUID = -2528903467902347L;
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("EEE, dd LLL uuuu HH:mm");

    private final String description;
    private boolean done;

    /**
     * Create a generic task.
     *
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Get the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get whether the task is done.
     *
     * @return whether the task is done
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Set the task's done state.
     *
     * @param done whether the task is done
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "\u2713" : " ", description);
    }
}
