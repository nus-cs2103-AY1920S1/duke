package duke.task;

/**
 * Encapsulates a task in real life.
 * It has a title as well as some addition details, depending on the type of the task.
 */
public abstract class Task {
    private String title;
    protected String type; // T for to do, E for event, and D for Deadline.
    private boolean isDone;
    private String status; // a unicode symbol representing the isDone status of the task (either a tick or a cross).
    protected String details; // The details of this task, varies according to the actual type of the task.
    protected String detailsForDatabase; // data summary used to write the task into the database of Duke.

    /**
     * Constructs a task object.
     *
     * @param s the title of the task.
     */
    protected Task(String s) {
        title = s;
        status = "\u2717";
    }

    /**
     * Marks a task as done.
     *
     * @return the task itself.
     */
    public Task markAsDone() {
        isDone = true;
        status = "\u2713";

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

    /**
     * Returns the data summary of this task to record this task in the database.
     *
     * @return the data summary of this task.
     */
    public String getSummaryForDatabase() {
        int status = isDone ? 1 : 0;
        return String.format("%s | %d | %s", type, status, detailsForDatabase);
    }

    /**
     * Returns the string representation of the task.
     * It takes the form of [type][done status][details].
     *
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type, status, details);
    }
}
