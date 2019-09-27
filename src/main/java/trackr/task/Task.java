package trackr.task;

/**
 * Represents a task input by the user.
 */
public class Task {

    /**
     * Name of task.
     */
    protected String taskName;

    /**
     * Boolean indicating whether task has been completed.
     */
    protected boolean isDone;


    /**
     * Class constructor assigning name and isDone attribute to the object.
     * @param taskName The name of the deadline task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * This method is used to set the task as completed.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Assigns name of task object.
     * @param taskName Name of task
     */
    public void setName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * This method is used to get the status of the task.
     * @return boolean This indicates whether the task has been completed
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * This method is used to get the string representing the status of the task.
     * @return String This represents whether the task has been completed
     */
    public String getStatusIcon() {
        return (isDone ? "+" : " ");
    }

    /**
     * This method is used to get the type of the task.
     * @return String This returns the type of the task
     */
    public String getType() {
        return "task";
    }

    /**
     * This method is used to get the string representing the task.
     * @return String This returns the string representing the task
     */
    public String getTypeIcon() {
        return "[]";
    }

    /**
     * This method is used to get the date of the task.
     * @return String This returns the string representing the date of the task
     */
    public String getDate() {
        return "";
    }

    /**
     * This method is used to get the name of the task.
     * @return String This returns the name of the task in the form of a string
     */
    @Override
    public String toString() {
        return taskName;
    }
}
