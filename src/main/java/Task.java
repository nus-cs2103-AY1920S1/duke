
/**
 * Represents a Task class which basically represents a task
 * a Task object is represented with a description, a isDone
 * boolean which shows whether the task is completed.
 */

public class Task {
    protected String description;
    protected boolean isDone;
    public static int total = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        total++;
    }

    public String getStatusIcon() {
        if(isDone) {
            return "\u2713";
        } else {
            return "\u2718";
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
