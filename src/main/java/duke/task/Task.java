package duke.task;

/**
 * An abstract class meant to be inherited by the types of Tasks that Duke accepts
 * e.g., <code>todo</code>, <code>event</code>, <code>deadline</code> tasks.
 */
public abstract class Task {
    protected String taskName;
    protected boolean isCompleted;
    protected String details;
    protected TypeOfTask taskType;

    /**
     * Default constructor for Task class
     *
     * @param taskName A String which represents the taskName
     *                 e.g., <code>todo borrow books</code>
     *                 where the taskName is "borrow books"
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Alternative constructor for Task class
     *
     * @param taskName    Information about the task
     *                    e.g., <code>todo borrow books</code>
     *                    where the taskInformation is "borrow books"
     * @param isCompleted A boolean indicating if the task is completed
     */
    public Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    /**
     * Converts task to String that will be scanned into preset task.text file
     *
     * @return A String representing the task in the specified format
     */
    public String convertTaskToFileString() {
        char isCompleted = this.isCompleted ? 'T' : 'F';
        String taskType = this.taskType.toString();
        String result = taskType + " | " + isCompleted + " | " + this.taskName + " " + this.details;
        return result.stripTrailing();
    }

    /**
     * Mark task as completed
     */
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * Get name of task
     *
     * @return taskName in String format
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Get details of task
     *
     * @return String representing extra details of task
     */
    public String getDetails() {
        return details;
    }

    /**
     * Get entire task represented in a String
     *
     * @return String representing a task instance
     */
    @Override
    public String toString() {
        char symbol = this.isCompleted ? '✓' : '✗';
        String result = "[" + taskType + "][" + symbol + "] " + taskName + " " + details;
        return result.stripTrailing();
    }
}
