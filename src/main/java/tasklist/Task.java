package tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  Abstract class for all tasks
 *  gurantees that all important methods are implemented
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime date;
    protected DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMMM hhmm a");

    public Task(String description, boolean completionStatus, LocalDateTime date) {
        this.description = description;
        this.isDone = completionStatus;
        this.date = date;
    }

    public Task(String description, boolean completionStatus) {
        this.description = description;
        this.isDone = completionStatus;
    }

    /**
     * Returns the current status of the task
     * @return returns either a tick or cross according to the completion status
     */
    public String getCurrentStatus() {
        return ((isDone ? "[✓] " : "[✗] ")); //return tick or X symbols
    }

    /**
     * overall status of task that includes name,completion status, description and date(if applicable)
     * to be implemented according to task type
     * @return the overall status in a assignment correct format
     */
    public abstract String getOverallStatus();

    /**
     * changes task status to done
     */
    public void CompleteTask() {
        isDone = true;
    }

    /**
     * prints the the full task details in to a format for easy storage and loading
     * to be implemented according to task type
     * @return a string that contains the details of the task in a format the parser can read
     */
    public abstract String encodeForStorage();
}