package jermi.task;

/**
 * Base class for task.
 */
public abstract class Task {
    /** Description of the task */
    private String description;
    /** State of the task: true if completed, else false */
    private boolean isDone;

    /** Constructor for class */
    Task(String description, String isDone) {
        this.description = description;
        this.isDone = isDone.equals("1");
    }

    /**
     * Returns a status icon.
     *
     * @return Tick if task is completed, else cross.
     */
    private String getStatusIcon() {
        return this.isDone ? "\u2713" : "\u2718";
    }

    /**
     * Sets status of task to completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a task type code.
     *
     * @return "T", "D" or "E" depending on task type.
     */
    abstract String getTypeCode();

    /**
     * Returns a string representation of task.
     *
     * @return A string representation of task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getTypeCode(), this.getStatusIcon(), this.description);
    }

    /**
     * Converts the task into a string in save format.
     *
     * @return A string in save format.
     */
    public String toSaveFormat() {
        return String.format("%s|%d|%s", this.getTypeCode(), this.isDone ? 1 : 0, this.description);
    }
}
