package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a task.
 */
public class Task {

    private boolean isDone;
    protected String description;
    protected Date time;
    protected String label;

    /**
     * Creates a new instance of <code>Task</code>.
     *
     * @param s description of the task.
     */
    public Task(String s) {
        this.isDone = false;
        this.description = s;
    }

    /**
     * Creates a new instance of <code>Task</code>.
     *
     * @param s description of the task.
     * @param t time of the task.
     */
    public Task(String s, Date t) {
        this.isDone = false;
        this.description = s;
        this.time = t;
    }

    /**
     * Returns a label which is a representation of the task type.
     *
     * @return the label of the task.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the time of the task.
     *
     * @return time of the task.
     */
    public Date getTime() {
        if (this.time != null) {
            return time;
        } else {
            return null;
        }
    }

    /**
     * Returns the time of the task as string.
     *
     * @return string representation of the time of the task.
     */
    public String getTimeAsString() {
        if (this.time != null) {
            return convertDateToString(time);
        } else {
            return "";
        }
    }

    /**
     * Returns the status of the task.
     *
     * @return status of the task.
     */
    public int getStatus() {
        return this.isDone ? 1 : 0;
    }

    /**
     * Returns the description of the task.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Updates status of task by marking this task as done.
     * Boolean value <code>isDone</code> is updated to <code>true</code>.
     */
    public void mark() {
        // Update status of task
        if (!isDone) {
            this.isDone = true;
        }
    }

    /**
     * Returns a string representing tick or X symbols.
     *
     * @return string representation of tick or X symbols.
     */
    public String getStatusIcon() {
        // Return tick or X symbols
        return (isDone ? "[\u2713] " : "[\u2718] ");
    }

    /**
     * Converts the format of date and time.
     *
     * @param date date and time of task.
     * @return Reformatted date and time.
     * @throws ParseException self-defined exceptions caused by illegal input.
     */
    private String convertDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy, hh:mm a");
        String taskTime = formatter.format(date);
        String[] array = taskTime.split(" ");
        array[0] = getOrdinal(Integer.parseInt(array[0]));
        array[array.length - 1] = array[array.length - 1].toLowerCase();
        StringBuilder taskTimeBuilder = new StringBuilder();
        for (String s : array) {
            taskTimeBuilder.append(" ").append(s);
        }
        taskTime = taskTimeBuilder.toString();
        taskTime = taskTime.trim();
        return taskTime;
    }

    /**
     * Converts numbers to ordinal numbers.
     *
     * @param n number as integers.
     * @return String representing ordinal number nth.
     */
    private static String getOrdinal(int n) {
        assert n > 0 : n;
        if (n >= 11 && n <= 13) {
            return n + "th";
        }
        switch (n % 10) {
        case 1:
            return n + "st of";
        // Fallthrough
        case 2:
            return n + "nd of";
        // Fallthrough
        case 3:
            return n + "rd of";
        // Fallthrough
        default:
            return n + "th of";
        // Fallthrough
        }
    }

}
