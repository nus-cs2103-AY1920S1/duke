import java.util.Comparator;
import java.util.Date;

/**
 * Parent class of the Deadline, Events and Todo objects.
 */
public abstract class Task {
    protected String taskDesc;
    protected boolean isDone;
    protected String type;
    protected Date date;

    /**
     * Constructs a task object.
     * @param desc task description as inputted by the user
     */
    public Task(String type, String desc) {
        this.type = type;
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
            return "[" + type + "][\u2713] " + taskDesc;

        } else {
            return "[" + type + "][\u274C] " + taskDesc;
        }
    }

    public static Comparator<Task> typeComparator = new Comparator<Task>() {
        @Override
        public int compare(Task t1, Task t2) {
            return (t1.type).compareTo(t2.type);
        }
    };

    public static Comparator<Task> dateComparator = new Comparator<Task>() {
        @Override
        public int compare(Task t1, Task t2) {
            return (t1.getDate()).compareTo(t2.getDate());
        }
    };

    /**
     * Converts the Deadline object to the String form to be saved in file.
     * @return String format of the object
     */
    public abstract String toFileFormat();

    public abstract Date getDate();

    /**
     * Returns a string corresponding to date stored in task object.
     * @return String
     */
    public String getDesc() {
        return this.taskDesc;
    }

}





