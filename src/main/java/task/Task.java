package task;

/**
 * Task which belongs to a list.
 *
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor of Task.
     *
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract String fileFormat();

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "[done]" : "[x]"); //return tick or X symbols
    }

    public String isDoneString() {
        return this.isDone ? "1" : "0";
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.getDescription());
    }

    //...
}