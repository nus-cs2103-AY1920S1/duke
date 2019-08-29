import java.util.Date;

/** Task to represent a To-do. */
class ToDo extends Task {

    /**
     * Constructor for the object.
     * @param name Description of to-do.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Get the task type of the task.
     * @return TO-DO value of the enum TaskType.
     */
    public TaskType getType() {
        return TaskType.TODO;
    }

    /**
     * Get the date of event.
     * @return Null because a to-do has no date.
     */
    public Date getDate() {
        return null;
    }

    /**
     * String method to override the toString() in java.lang.Object.
     * @return String representation of the object.
     */
    @Override
    public String toString() {
        String doneStr = this.done ? "✓" : "✗";
        return String.format("[T][%s] %s", doneStr, this.name);
    }
}