/**
 * Represents a task that the user has to do/attend
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Initialises a Task object with a description
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the done status of the task
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns user-readable string of the task
     * @return user-readable string
     */
    @Override
    public String toString () {
        return "[" + getStatusIcon() + "]" + " " + this.description;
    }

    /**
     * Marks the current task's done status as true
     */
    public void markAsDone () {
        this.isDone = true;
    }

    /**
     * Formats the string so that it can be saved
     * @return
     */
    public abstract String saveFormat();

    //...
}
