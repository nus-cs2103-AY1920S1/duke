import java.util.Date;

/**
 * Parent class of the Deadline, Events and Todo objects.
 */
public abstract class Task {
    protected String taskDesc;
    protected boolean isDone;

    /**
     * Constructs a task object.
     * @param desc task description as inputted by the user
     */
    public Task(String desc) {
        this.taskDesc = desc;
        this.isDone = false;
    }

    /**
     * Updates the status of completion of a task to complete.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Converts the task object to its string form to be printed.
     * @return String printing out the task object's description
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[✓] " + taskDesc;

        } else {
            return "[✗] " + taskDesc;
        }
    }

    /**
     * Converts the Deadline object to the String form to be saved in file.
     * @return String format of the object
     */
    public abstract String toFileFormat();

    /**
     * Returns a string corresponding to date stored in task object.
     * @return String
     */
    public String getDesc() {
        return this.taskDesc;
    }

}





