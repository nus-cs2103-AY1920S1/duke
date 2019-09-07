/**
 * Represents the tasks the user enters into Duke.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to set the description of the Task.
     *
     * @param desc The description of the Task
     */
    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    /**
     * Constructor to set the description of the Task and whether it is done.
     *
     * @param desc   The description of the Task
     * @param isDone The boolean variable to note if Task is completed
     */
    public Task(String desc, boolean isDone) {
        this.description = desc;
        this.isDone = isDone;
    }

    /**
     * Returns a Tick or X symbol based on whether the Task is completed.
     *
     * @return The status icon of the Task object
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Sets the boolean variable isDone to true, marking the Task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the description of the Task with its status.
     *
     * @return Task description and status in the format of "[Status] description"
     */
    public String getTask() {
        String output = "[" + getStatusIcon() + "] " + description;
        return output;
    }

    /**
     * Returns the boolean variable isDone of the Task object.
     *
     * @return status of task
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the Task's description.
     *
     * @return description of Task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the Task object in a String format for saving into a text file.
     *
     * @return description and status of Task for saving
     */
    public String writeFormat() {
        return "T " + isDone + " " + description;
    }

    /**
     * Overrides toString method for printing Task object, which includes Task type.
     *
     * @return display format of Task, "[T][Status] description"
     */
    @Override
    public String toString() {
        return "[T]" + getTask();
    }
}
