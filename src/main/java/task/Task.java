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

    public TaskType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

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

        switch (type) {
        case TODO:
            return description;
        case DEADLINE:
            return description + " (by: " + timeString + ")";
        case EVENT:
            return description + " (at: " + timeString + ")";
        default:
            return description;
        }
    }
}