
/**
 * Represents a Task class which basically represents a task
 * a Task object is represented with a description, a isDone
 * boolean which shows whether the task is completed.
 */

public class Task {
    protected String description;
    protected boolean isDone;
    public static int total = 0;

    /**
     * Constructor for Task object. Takes in task description
     * @param description user input task name, details
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        total++;
    }

    /**
     * Gets the status of a task, whether it is done or not.
     * @return String tick symbol
     */
    public String getStatusIcon() {
        if (isDone) {
            return "o";
        } else {
            return "x";
        }
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
