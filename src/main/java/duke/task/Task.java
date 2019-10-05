package duke.task;

/**
 * The base class which for all tasks in the <code>Duke</code> application
 * which allows for the user to categorise tasks to be added into the task list.
 *
 * @author Clarence Koh
 * @version 1.0
 * @since 29th August 2019
 */

public class Task {

    /**
     * Represents the description of the task.
     */
    protected String description;

    /**
     * Represents whether the task has been completed and marked as done.
     */
    protected boolean isDone;

    /**
     * Class constructor specifying the description of the task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method displays to the user to show whether a task is done or not.
     *
     * @return The symbol which signifies whether the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "V" : "X"); // return tick or X symbols
    }

    /**
     * This method marks tasks as completed.
     */
    public void markedAsDone() {
        this.isDone = true;
    }

    public String toString() {

        return this.description;
    }

    /**
     * This method tells users whether the task has been completed or not.
     *
     * @return <code>boolean</code> telling the user whether the task was done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }
}