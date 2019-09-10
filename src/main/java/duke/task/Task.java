package duke.task;

/**
 * Encapsulates a task in real life.
 * It has a title as well as some addition details, depending on the type of the task.
 */
public abstract class Task {
    protected String title;
    protected String type; // T for to do, E for event, and D for Deadline.
    protected boolean isDone;
    protected String status; // a unicode symbol representing the isDone status of the task (either a tick or a cross).

    public enum TaskType {
        TODO, DEADLINE, EVENT;
    }

    /**
     * Constructs a task object.
     *
     * @param s the title of the task.
     */
    protected Task(String s) {
        title = s;
        status = "\u2717"; // A cross symbol indicating the task has not been done.
    }

    /**
     * Returns the data summary of this task to record this task in the database.
     *
     * @return the data summary of this task.
     */
    public abstract String getSummaryForDatabase();

    /**
     * Marks a task as done.
     *
     * @return the task itself.
     */
    public Task markAsDone() {
        isDone = true;
        status = "\u2713"; // A tick symbol indicating the task is done.

        return this;
    }

    /**
     * Checks if a task is done.
     *
     * @return a boolean value indicating whether the task is done or not.
     */
    public boolean isDone() {
        return isDone;
    }
}
