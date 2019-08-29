/**
 * Represents the Task object.
 */
public class Task {
    private boolean isDone;
    protected String description;
    protected String timeLabel;
    protected String wordLabel;

    /**
     * Creates a new instance of Task.
     *
     * @param description Stores description of the task.
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    /**
     * Creates the new instance of Task with timeLabel parameter as well.
     *
     * @param description Stores description of the task.
     * @param timeLabel Stores the time value for specific types of tasks.
     */
    public Task(String description, String timeLabel) { //Requires both type of parameterised constructors
        this.isDone = false;
        this.description = description;
        this.timeLabel = timeLabel;
    }

    /**
     * Returns the label of the task.
     *
     * @return the letter of the task.
     */

    public String getLabel() {
        return wordLabel;
    }

    /**
     * Returns the time of the task, when applicable.
     * @return the time of task.
     */
    public String getTimeLabel() {
        if (this.timeLabel != null) {
            return timeLabel;
        } else {
            return "";
        }
    }

    /**
     * Returns the status if the task is done or not.
     *
     * @return 0, 1 status of the task.
     */
    public int getInfo() {
        return this.isDone ? 1 : 0;
    }

    /**
     * Returns description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Updates the status of the task by marking if the task is done.
     */
    public void mark() {
        if (!isDone)
            this.isDone = true;
    } //update status of task if completed

    /**
     * Returns a string representing tick or X symbols for done/not done task.
     *
     * @return String of tick or X.
     */
    protected String getStatusIcon() {
        return (isDone ? "[\u2713] " : "[\u2718] "); //return tick or X symbols
    }
}