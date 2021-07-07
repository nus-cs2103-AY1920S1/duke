/**
 * Task of Duke.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Task Constructor with description and at as string.
     * @param description description of tasks
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * returns done status. Tick if done and cross if not done.
     * @return status icon as string
     */
    //✓u2713
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    /**
     * mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
