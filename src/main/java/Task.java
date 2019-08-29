import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Task object that can represent todos, deadlines or events.
 * Every task object can be marked as done when it's completed.
 */
public class Task {
    public static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("dd MMMM yyyy, h:mm a");
    public static final DateTimeFormatter FILE_DATE_TIME = DateTimeFormatter.ofPattern("dd/MM/yy hhmm");
    
    protected String name;
    protected boolean isDone;
    protected String type;


    public Task() {

    }

    /**
     * Creates a task object that stores the type of task it is, i.e todos, deadlines or events and its description.
     *
     * @param type Type of task, represented by "T", "D" or "E".
     * @param name Description of the task.
     */
    public Task(String type, String name) {
        this.type = type;
        this.name = name;
        this.isDone = false;
    }

    /**
     * Creates a task object that stores the type of task it is, i.e todos, deadlines or events and its description.
     * This constructor is used when loading tasks from the datafile as it checks if the task has already been marked
     * as done.
     *
     * @param type Type of task, represented by "T", "D" or "E".
     * @param isDone Boolean representing whether the task is done.
     * @param name Description of the task.
     */

    public Task(String type, String isDone, String name) {
        this.type = type;
        this.name = name;
        if (isDone.equals("1")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }


    public String getName() {
        return this.name;
    }

    /**
     * Marks a task as done and returns a string telling users that the task has been marked as done.
     *
     * @return Message displaying the task that has been marked as done.
     */
    public String markAsDone() {
        this.isDone = true;

        return String.format("Nice! I've marked this task as done:\n  [%s] %s", "v", name);
    }

    /**
     * Returns a formatted string representing the state of the task to be stored in a data file.
     *
     * @return String formatted for storing in a file.
     */
    public String fileFormat() {
        return String.format("%s | %s | %s\n", type, isDone ? "1" : "0", name);
    }

    /**
     * String representing a task and its state when user lists all tasks.
     *
     * @return String in the format for the list command.
     */
    public String toString() {
        return String.format("[%s][%s] %s", type, isDone ? "v" : "x", name);
    }
}
