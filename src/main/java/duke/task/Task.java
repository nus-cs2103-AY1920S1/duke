package duke.task;

/**
 * Represents a task. Contains a description and a flag that indicates if the task has been completed or not.
 * It is an abstract entity whose children are deadlines, events and to-dos.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected static final String INVALID_DATE_MSG = "Invalid date format! "
            + "Please ensure your date sticks to this format:\n"
            + "    Deadlines : \"DD/MM/YYYY HHMM\"\n"
            + "    Events : \"DD/MM/YYYY HHMM-HHMM\"";

    /**
     * Constructor for an abstract task.
     * @param description Description of the task.
     * @throws InvalidTaskDukeException If the task is invalid.
     */
    public Task(String description) throws InvalidTaskDukeException {
        this.description = description;
        if (description.isBlank()) {
            throw new InvalidTaskDukeException("Description cannot be empty! Please enter a valid description.");
        }
        this.isDone = false;
    }

    /**
     * Returns a tick if the task is complete, cross otherwise.
     * @return Status symbol indicating completion.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns the description of the task.
     * @return Description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the task's completion flag.
     * @param isDone Boolean to be set.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the completion flag of the task.
     * @return Boolean
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the time associated with the task.
     * @return String representing time.
     */
    public abstract String getTime();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}