/**
 * Encapsulates a task in real life.
 * It has a title as well as some addition details, depending on the type of the task.
 */
public abstract class Task {
    private String title;
    protected String type; // T for to do, E for event, and D for Deadline.
    private boolean done;
    private String status;
    // varies according to the actual type, definitely includes topic, other optional included fields are date.
    protected String details;

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
    public void markAsDone() {
        done = true;
        status = "\u2713";
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
