import java.text.SimpleDateFormat;
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
     * Returns a boolean indicating the status of completion of the task.
     * @return true if the task is completed and false otherwise
     */
    public boolean getStatus() {
        return isDone;
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
        if(isDone) {
            String printable = "[✓] " + taskDesc;
            return printable;
        } else {
            String printable ="[✗] " + taskDesc;
            return printable;
        }
    }

    /**
     * Converts the Deadline object to the String form to be saved in file.
     * @return String format of the object
     */
    public abstract String toFileFormat();

    /**
     * Retrieves the time description of the object.
     * @return Time description of the object in calendar date format
     */
    public abstract Date getDate();

}

