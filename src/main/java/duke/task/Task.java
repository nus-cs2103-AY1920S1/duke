package duke.task;

/**
 * Represents a task.  <code>Task</code> is an abstract class that can not be 
 * instantiated, but it has many child classes to represent different types of 
 * tasks.
 */
public abstract class Task {
    /**
     * A String that represents the specific content of the task.
     */
    protected String description;

    /**
     * A boolean that represents the status of completion of the task.
     */
    protected boolean isDone;

    /**
     * Initialises the minimum fields required to setup a <code>Task</code>.
     * All new tasks are uncompleted by default.
     * @param description A string that represents the specific activity
     *                  associated with the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon that represents the status of the task.
     * @return Tick if completed, cross if uncompleted.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns the description of the task.
     * @return A string that represents the specific activity associated with
     *          the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Formats the task in a pecific format for clear display and storage.
     * @return A string representatio of the task to be stored in a local file.
     */
    public abstract String format();

    /**
     * Overrides the <code>toString()</code> method in <code>Object</code>
     * class to output a string representation of the task.
     *  @return A string representation of the task that displays the status 
     *          and description of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}

