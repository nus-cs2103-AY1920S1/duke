package cs2103t.duke.task;

/**
 * Represents tasks, each with 3 main attributes: task type, description, whether it is completed.
 */
public abstract class Task {
    /** Tick symbol. */
    public static final String TICK = "\u2713";
    /** Cross symbol. */
    public static final String CROSS = "\u2717";

    /** Description of task. */
    protected String description;
    /** Whether task is completed. */
    protected boolean completed;
    /** Task type of task. */
    protected TaskType taskType;

    //getter mtds
    /**
     * Gets description of task.
     * @return description (notes, if any).
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets whether task is completed.
     * @return true if completed, false otherwise.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Gets task type of task.
     * @return TaskType of task.
     */
    public TaskType getTaskType() {
        return this.taskType;
    }

    //setter mtds

    /**
     * Sets task to completed.
     */
    public void setCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        String checked;
        if (this.completed) {
            checked = TICK;
        } else {
            checked = CROSS;
        }
        return String.format("[%s][%s] %s", this.taskType, checked, this.description);
    }
}
