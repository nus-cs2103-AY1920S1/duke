package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Task object that can represent todos, deadlines or events.
 * Every task object can be marked as done when it's completed.
 */
public class Task {
    /** DateTimeFormatter that parses user input of date & time to format to be displayed in Duke. */
    public static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("dd MMMM yyyy, h:mm a");
    /** DateTimeFormatter that parses the LocalDateTime to a format to be stored in the data file */
    public static final DateTimeFormatter FILE_DATE_TIME = DateTimeFormatter.ofPattern("dd/MM/yyyy hhmm");

    /** Description of the task */
    protected String name;
    /** Boolean representing whether the task is done */
    protected boolean isDone;
    /** Type of the task: Todo, Deadline or Event */
    protected String type;
    /** Priority of the task: High, Medium or Low */
    protected String priority;

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
    public Task(String type, String isDone, String name, String priority) {
        this.type = type;
        this.name = name;
        if (isDone.equals("1")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
        if (priority.equals("none")) {
            this.priority = null;
        } else {
            this.priority = priority;
        }
    }

    /**
     * Returns the description of the task.
     *
     * @return String representing the task description.
     */
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

    public String setPriority(String priority) {
        this.priority = priority;
        return String.format("I've set the priority of this task:\n [%s][%s] %s <Priority: %s>",
                type ,isDone ? "v" : "x", name, priority);
    }
    /**
     * Returns a formatted string representing the state of the task to be stored in a data file.
     *
     * @return String formatted for storing in a file.
     */
    public String fileFormat() {
        return String.format("%s | %s | %s | %s\n",
                type, isDone ? "1" : "0", name, priority == null ? "none" : priority);
    }

    /**
     * String representing a task and its state when user lists all tasks.
     *
     * @return String in the format for the list command.
     */
    public String toString() {
        return String.format("[%s][%s] %s %s",
                type, isDone ? "v" : "x", name, priority == null ? "" : "<Priority: " + priority + ">");
    }
}
