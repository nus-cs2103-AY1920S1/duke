package duke.task;

/**
 * Task with description and completion state.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     *Constructor of a task.
     * @param description description of task
     */
    Task(String description) {
        assert description.length() > 0;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status symbol of completion status.
     * @return String Y if done and N is not done
     */
    private String getStatusIcon() {
        return (isDone ? "Y" : "N");
    }

    /**
     * Sets the state of current task to done.
     */
    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
