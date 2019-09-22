package duke;

/**
 * The Task class contains methods for query of the tasks
 * as well as to sets tasks as done.
 */
public class Task {

    private String description;
    private boolean isDone;
    private boolean isPriority;

    /**
     * Constructor for Task class.
     *
     * @param description The task.
     * @param isPriority Priority level of task.
     */
    public Task(String description, boolean isPriority) {
        this.description = description;
        this.isPriority = isPriority;
        this.isDone = false;
    }

    /**
     * Returns status symbol symbol of a task.
     *
     * @return symbol Plus symbol if task is done and blank otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "+" : " ");
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the priority level of the task,
     * true if the task is a priority and false otherwise.
     *
     * @return isPriority The priority level of the task.
     */
    public boolean isPriority() {
        return isPriority;
    }

    /**
     * Returns the priority level of the task,
     * "H" if the task is a priority and "L" otherwise.
     *
     * @return priorityLevel The priority level of the task.
     */
    public String getPriority() {
        String priorityLevel = isPriority ? "H" : "L";
        return priorityLevel;
    }

    /**
     * Sets the task to high priority.
     */
    public void setAsPriority() {
        isPriority = true;
    }

    /**
     * Returns an int to indicate if the task is done.
     *
     * @return int 1 is used to indicate a task as done, 0 otherwise.
     */
    public int isDone() {
        return isDone ? 1 : 0;
    }

    /**
     * Sets the task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Overrides the toString method to print the task.
     *
     * @return String Task formatted to string including status icon.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}