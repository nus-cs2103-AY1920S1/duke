/**
 * Encapsulates a Task object which stores activity name, status (done or not done).
 */

public class Task {

    /** 2 attribute.
     * tasName represents the String of task name.
     * done is true if the task is done, or false otherwise.
     */
    protected String taskName;
    protected boolean done;

    /**
     * The constructor takes in a String of taskName and a boolean variable
     * to construct a Task object.
     * @param taskName a String of task / activity name.
     * @param done true if the task is done or false otherwise.
     */
    public Task(String taskName, boolean done) {
        this.taskName = taskName;
        this.done = done;
    }

    /**
     * Changes the status of the task when a "done" command is executed.
     */
    public void markAsDone() {
        done = true;
    }

    @Override
    /**
     * Returns a String of representation of the task.
     * @return a String showing the status and the task name.
     */
    public String toString() {
        if (done) {
            return "[✓]" + taskName;
        } else {
            return "[✗]" + taskName;
        }
    }

    /**
     * Returns a dummy string.
     * @return a dummy string.
     */
    public String storageFormat() {
        return "";
    }
}