package dukepkg;

/**
 * The Task object.
 */
public class Task {
    private boolean done_ = false;

    private final String task_;

    /**
     * Instantiates a new Task.
     *
     * @param task the task
     */
    Task(String task) {
        task_ = task;
    }

    /**
     * Flag for whether the task is finished.
     *
     * @return the boolean
     */
    public boolean isDone() {
        return done_;
    }

    /**
     * Gets task.
     *
     * @return the task
     */
    public String getTask() {
        return task_;
    }

    /**
     * Mark the task as finished.
     */
    public void markDone() {
        done_ = true;
    }

    private String getSymbol() {
        if(isDone()) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    @Override
    public String toString() {
        return "[" + getSymbol() + "] " + task_;
    }
    public boolean equals(Object obj) {
        Task o = (Task) obj;
        return (this.task_.equals(o.task_));
    }
}
