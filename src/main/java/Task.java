import java.util.ArrayList;

/**
 * Represents a task.
 * Contains the description and type of the task.
 * Contains boolean flag to determine if task has been done.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    public static ArrayList<Task> tasks = new ArrayList<>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "";
    }

    /**
     * Determines if task is done.
     *
     * @return O if done, X if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "O" : "X"); //return tick or X symbols
    }

    /**
     * Gets time of the task for Events and Deadlines.
     * Does nothing for ToDos or Tasks.
     *
     * @return tick symbol if done, X symbol if not done.
     */
    public String getTime() {
        return "";
    }

    /**
     * Parses the time input the understand date.
     * Does nothing for ToDos or Tasks.
     *
     * @throws Exception If unable to understand date.
     */
    public void understandDate() throws Exception {
    }

}
