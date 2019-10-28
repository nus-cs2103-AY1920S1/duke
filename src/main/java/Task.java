/**
 * Represents a task that a user is to do.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Construct a task object that specifics its details.
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    //set methods
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String printTask = "[" + this.getStatusIcon() + "] " + this.getDescription();
        return printTask;
    }
}