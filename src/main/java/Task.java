/**
 * Represents a Task object.
 * Super class of ToDos, Events, and Deadlines.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a task object with description as input.
     * @param des description of task.
     */
    public Task(String des) {
        description = des;
        isDone = false;
    }

    /**
     * Returns an icon according to whether the task is done or not.
     * @return '+' if task is done and '-' if task is not done.
     */
    public String getStatusIcon(){
        return isDone ? "+" : "-";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns description of Task.
     * @return description of Task.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
