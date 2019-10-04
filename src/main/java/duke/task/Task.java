package duke.task;

/**
 * This class is ued to represent the task and its status.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * The constructor of the class, assign the string value to the variable and set the status to undone.
     *
     * @param description The description of the task.
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * To return the status of the task.
     *
     * @return The string of tick or cross depending on the status.
     */
    private String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * To set the status to be done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "- " + getStatusIcon() + " - " + description;
    }
}
