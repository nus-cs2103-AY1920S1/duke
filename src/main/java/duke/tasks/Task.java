package duke.tasks;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Task implements Serializable {

    protected final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    protected String description;
    protected boolean isDone;

    /**
     * Create a Task instance.
     * @param description information about the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get status icon according the to isDone status.
     * @return a status icon indicating whether the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Get description of the task.
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Get the isDone status of the task.
     * @return whether the task is done
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Generate the text representation of the task in display format.
     * @return the representation of the task in display format.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
