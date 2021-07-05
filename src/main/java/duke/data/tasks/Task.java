package duke.data.tasks;

/**
 * Implements a task.
 * @author Lim Yong Shen, Kevin
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the specified description.
     * @param description The specified description.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns true if this task is done.
     * @return true if this task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the command String (user input) used to create this task.
     * @return The command String (user input) used to create this task.
     */
    public abstract String getCommandString();

    /**
     * Returns this task's description.
     * @return This task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets this task as done.
     */
    public void setAsDone() {
        isDone = true;
    }

    /**
     * Returns the string representation of this task.
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        String statusIcon = isDone ? "Done" : "Not Done";
        return String.format("[%s] %s", statusIcon, description);
    }

}
