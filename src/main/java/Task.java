/**
 * Represent a task.
 */
public class Task {
    /**
     * Task description.
     */
    private String desc;
    /**
     * Indicator for task completion.
     */
    private boolean isDone;

    /**
     * Constructor for Task.
     * @param desc Task description.
     */
    Task(String desc) {
        this.desc = desc.strip();
        this.isDone = false;
    }

    /**
     * Sets task as done.
     */
    public void done() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s",
                this.isDone ? '\u2713' : '\u2717',
                this.desc);
    }
}
