/**
 * Represents a basic task. A task object includes its task description
 * and a boolean to declare whether the task is done
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Represents a Task.
     * @param description description of task
     */
    public Task(String description) {
        assert description != "" : "description of task cannot be empty";
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0"); //return tick or X symbols
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeDateTime(DateTime dateTime) {

    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
