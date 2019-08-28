package duke.task;

/**
 * Task is an abstract class from which other tasks are built upon.
 * Tasks represents the tasks that a person has when using Duke.
 */
public abstract class Task {
    String taskDetails;
    boolean completed;
    Task(String taskDetails) {
        this.taskDetails = taskDetails;
        this.completed = false;
    }

    /**
     * Marks a task as completed.
     */
    void taskDone() {
        this.completed = true;
    }

    /**
     * Returns a string of a task that can contain
     * its description, time and completion status.
     *
     * @return string that contains information about a task.
     */
    abstract String saveInfo();

    /**
     * Returns the completion status of a task.
     *
     * @return task completion status.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * A getter for task details.
     *
     * @return the task details.
     */
    public String getTaskDetails() {
        return this.taskDetails;
    }
    @Override
    public String toString() {
        if (completed) {
            return "[✓] " + this.taskDetails;
        } else {
            return "[✗] " + this.taskDetails;
        }
    }
}
