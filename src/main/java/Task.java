/**
 * Represents a task.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713] " : "[\u2718] "); // returns check mark if done, cross symbol if undone
    }

    public String getDescription() {

        return (description);
    }

    public void markAsDone(Task t) {

        t.isDone = true;
    }
}
