package duke.task;

/**
 * Represents a task object with a description of the task and
 * an indication of whether the task is done.
 */
public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Returns description of the task.
     *
     * @return description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a Boolean indicating whether the task is done.
     *
     * @return whether task is done.
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Sets the task to be done.
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * Returns a string representing the type of task.
     *
     * @return string representing type of task.
     */
    public String getTaskType() {
        //to be reviewed
        return null;
    }

    /**
     * Returns string description of the task.
     *
     * @return string description of task.
     */
    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        if (this.isDone()) {
            temp.append("[done] ");
        } else {
            temp.append("[undone] ");
        }
        temp.append(this.description);
        return temp.toString();
    }
}