package task;

/**
 * Represents the task, inherited by Deadlines, Events, ToDos.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates the Task object.
     *
     * @param description Task message.
     * @param isDone      Marks the task as done nor not done.
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
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

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}