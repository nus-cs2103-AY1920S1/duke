package duke.task;

import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * The super-class of all Task objects.
 */
public class Task {
    /**
     * The description of the Task, as inputted by the user.
     */
    private String description;
    /**
     * The completion status of the Task.
     */
    private boolean isDone;
    /**
     * The DateTimeFormatter for LocalDateTime objects.
     */
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy, h:mm a");
    /**
     * The DateTimeFormatter for storage format of LocalDateTime objects.
     */
    static final DateTimeFormatter STORAGE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy hhmm");
    
    /**
     * Creates a Task object.
     * The default completion status of the Task is 'not done'.
     *
     * @param description The description of the Task, as inputted by the user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    /**
     * Gets the description of this Task object.
     *
     * @return Returns the description of this Task object, as a String.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a cosmetic symbol to indicate completion status of the Task.
     * Symbol is '+' for completed Tasks and '-' for incomplete Tasks.
     *
     * @return Returns a String symbol.
     */
    public String getStatusIcon() {
        return (isDone ? "+" : "-");
    }
    
    /**
     * Marks this Task as done.
     * Completion status of this Task is always set to done, even if this method is executed multiple times.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    
    /**
     * Returns a default formatted String for writing data to the user's hard drive.
     * Default String contains completion status and description of this Task.
     *
     * @return Returns a String.
     */
    public String formattedString() {
        return this.getStatusIcon() + " | " + this.description;
    }
    
    /**
     * Returns a default String for printing to the user's console.
     * Default String contains completion status and description of this Task.
     *
     * @return Returns a String.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
