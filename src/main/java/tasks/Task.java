package tasks;

/**
 * Task object is defined by a description. It has a field isDone to indicate
 * whether the task is completed or not.
 */
public class Task {

    private String description;
    private boolean isDone;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the task as done.
     */
    public void setAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a status icon, based on whether the task is completed or not.
     * @return String representing the status icon
     */
    public String getStatusIcon() {
        // X to represent done, nothing to represent not done
        return (isDone ? "X" : " ");
    }

    public boolean getBoolean() {
        return isDone;
    }
}