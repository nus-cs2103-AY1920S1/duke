package tasklist;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstract class for all tasks.
 * gurantees that all important methods are implemented
 */
public abstract class Task {
    protected SimpleStringProperty description;
    protected SimpleBooleanProperty isDone;
    protected LocalDateTime date;
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMMM hhmm a");

    /**
     * First of two constructors for deadline and event tasks.
     */
    public Task(String description, boolean completionStatus, LocalDateTime date) {
        this.description = new SimpleStringProperty(description);
        this.isDone = new SimpleBooleanProperty(completionStatus);
        this.date = date;
    }

    /**
     * Second of two constructors for todo tasks.
     */
    public Task(String description, boolean completionStatus) {
        this.description = new SimpleStringProperty(description);
        this.isDone = new SimpleBooleanProperty(completionStatus);
    }

    /**
     * Returns the current status of the task.
     * @return returns either a tick or cross according to the completion status
     */
    public String getCurrentStatus() {
        return ((isDone.getValue() ? "[✓] " : "[✗] ")); //return tick or X symbols
    }

    /**
     * overall status of task that includes name,completion status, description and date(if applicable).
     * to be implemented according to task type.
     * @return the overall status in a assignment correct format
     */
    public abstract String getOverallStatus();

    /**
     * changes task status to done.
     */
    public void completeTask() {
        isDone.setValue(true);
    }

    /**
     * prints the the full task details in to a format for easy storage and loading.
     * to be implemented according to task type.
     * @return a string that contains the details of the task in a format the parser can read
     */
    public abstract String encodeForStorage();
}