package softeng.tasks;

/**
 * Represents a task. A <code>Task</code> object corresponds to a thing we need to note down
 * e.g. toDo return book
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Retrieves the done/not done status of the task.
     * @return 0 representing "not done" and 1 representing "done"
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Marks a task as done.
     */
    public void mardAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Converts the task to a format for saving in local file.
     * @return
     */
    public String toSave() {
        return this.description + this.isDone;
    }

    public boolean match(String str) {
        return description.contains(str);
    }
}
