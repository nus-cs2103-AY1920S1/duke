package duke.task;

/**
 * Tasks are used in the entire program.
 */
public abstract class Task {
    /** Task description. **/
    private String description;

    /** Status of task. **/
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public abstract String getType();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "x" : " "); // return tick or no symbol
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markAsUnfinished() {
        isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getType(), getStatusIcon(), description);
    }
}