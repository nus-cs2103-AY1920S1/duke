package duke.model.task;

/**
 * An abstract class representing a Task.
 */
public abstract class Task {

    protected boolean isDone;
    protected String description;

    /**
     * Constructor for task, to be called when instantiating subclasses.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    /**
     * Another constructor for task, to be called when storage loads from data stored locally.
     *
     * @param description The description of the task.
     * @param isDone Defines whether a task is done.
     */
    public Task(String description, boolean isDone) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Returns the status icon of whether a task is done.
     *
     * @return Returns a tick if a task is done, otherwise, a cross.
     */
    private String getStatusIcon() {
        return (isDone ? "v" : "x");
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string which is formatted to be stored in local storage.
     *
     * @return Returns a string which is formatted to be stored in local storage.
     */
    public abstract String getFileStringFormat();
}
