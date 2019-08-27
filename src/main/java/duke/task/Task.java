package duke.task;

/**
 * Encapsulates a task in real life.
 * It has a title as well as some addition details, depending on the type of the task.
 */
public abstract class Task {
    private String title;
    protected String type; // T for to do, E for event, and D for Deadline.
    private boolean isDone;
    private String status;
    protected String details; // The details of this task, varies according to the actual type of the task.
    protected String detailsForDatabase; // data summary used to write the task into the database of Duke.

    /**
     * Construct a task object.
     *
     * @param s the title of the task.
     */
    public Task(String s) {
        title = s;
        status = "\u2717";
    }

    /**
     * Mark a task as done.
     */
    public Task markAsDone() {
        isDone = true;
        status = "\u2713";

        return this;
    }

    /**
     * Returns the data summary of this task to record this task in the database.
     * @return the data summary of this task.
     */
    public String getSummaryForDatabase() {
        int status = isDone ? 1 : 0;
        return String.format("%s | %d | %s", type, status, detailsForDatabase);
    }

    /**
     * Returns the string representation of the task.
     * It takes the form of [type][done status][details].
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type, status, details);
    }
}
