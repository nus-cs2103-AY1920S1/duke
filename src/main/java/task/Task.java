package task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Represents a task that the user wants Duke to keep track of.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;
    protected Calendar time;
    protected Priority priority;

    /**
     * Creates a new task.
     *
     * @param description description of the task.
     * @param type type of the task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Sets the date and the time associated with this task.
     *
     * @param time String in the format of dd/mm/yyyy hhmm
     */
    public void setTime(Calendar time) {
        this.time = time;
    }

    /**
     * Returns the task type of this task.
     *
     * @return task type of this task;
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Returns the description of this task.
     *
     * @return description of this task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the time of this task.
     *
     * @return time of this task in String representation in milliseconds.
     */
    public String getTime() {
        return time.getTimeInMillis() + "";
    }

    /**
     * Returns the status icon of this task.
     *
     * @return Status icon of this task. A tick means that the task is done and a cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    /**
     * Returns the String representation of this task's icon.
     *
     * @return String representation of this task's icon.
     */
    public String getTypeIcon() {
        String result = "";
        switch (type) {
        case TODO:
            result = "T";
            break;
        case DEADLINE:
            result = "D";
            break;
        case EVENT:
            result = "E";
            break;
        default:
            break;
        }

        return "[" + result + "]";
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Sets the priority level of this task.
     *
     * @param priority priority level of this task.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Returns the priority level of this task.
     *
     * @return priority level of this task.
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Returns the String representation of this task, which the user will see on the console.
     *
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        String timeString = "";
        if (time != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
            timeString = formatter.format(time.getTime());
        }

        String priorityString = "";
        if (priority != null) {
            priorityString = " [Priority: " + priority.toString() + "]";
        }

        switch (type) {
        case TODO:
            return description + priorityString;
        case DEADLINE:
            return description + " (by: " + timeString + ")" + priorityString;
        case EVENT:
            return description + " (at: " + timeString + ")" + priorityString;
        default:
            return description + priorityString;
        }
    }
}