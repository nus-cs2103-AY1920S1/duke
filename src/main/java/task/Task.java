package task;

public class Task {

    private String desc;
    protected Boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Get description.
     * @return the description of the task.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Get status.
     * @return the status of a task ([✘] or [✓]).
     */
    private String getStatus() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Get done status.
     * @return the status of a task(0 or 1).
     */
    public int getDone() {
        return isDone ? 1 : 0;
    }

    /**
     * mark a task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return ("[" + getStatus() + "]" + " " + getDesc());
    }

}
