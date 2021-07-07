
public class Task {
    private String taskName;
    private boolean isDone; //whether a task has been done or not

    /**
     * @param task name of a given task object
     *
     */
    public Task(String task) {
        taskName = task;
        isDone = false;
    }

    /**
     * Returns name of a Task instance.
     *
     */

    public String getTask() {
        return this.taskName;
    }

    /**
     * Sets isDone attribute to true, to denote a given Task instance is completed.
     *
     */
    public boolean setCompleted() {
        this.isDone = true;
        return true;
    }

    /**
     * Returns isDone value.
     *
     */

    public boolean isDone() {
        return this.isDone;
    }

}
