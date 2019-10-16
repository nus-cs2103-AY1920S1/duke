package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A task class that represents a task. It usually contains the description and boolean
 * which indicates whether it is done.
 */
public class Task {
    String content;
    boolean done;
    static final char tick = '✓';
    static final char cross = '✗';
    static final SimpleDateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    static final SimpleDateFormat outputFormatter = new SimpleDateFormat("E, dd MMM yyyy ha");

    /**
     * Initialize the task with a string content and the done state false.
     * @param content The description of the task.
     */
    public Task(String content) {
        this.content = content.trim();
        this.done = false;
    }

    /**
     * Since not all tasks have a time attribute, this method returns empty
     * string for task that does not have a time.
     * @return String, an empty string.
     */
    public String getTime() {
        return "";
    }

    /**
     * Get the content of the task.
     * @return String content of the task.
     */
    public String getContent() {
        return content;
    }

    /**
     * Different task have different format to output thus this is a simpler version
     * which just return the content.
     * @return String content of the task.
     */
    public String toString() {
        return this.content;
    }

    /**
     * Toggle the done state of the task. Will change done attribute from true to false and
     * false to true.
     */
    public void toggleState() {
        this.done = true;
    }

    /**
     * Checks if the task is done.
     * @return Boolean done.
     */
    public boolean isDone() {
        return done;
    }

    public void reschedule(Date date) {
    }
}