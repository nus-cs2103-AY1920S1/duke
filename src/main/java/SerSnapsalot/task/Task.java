package SerSnapsalot.task;

import java.util.ArrayList;

/**
 * Represents a task.
 * Contains the description and type of the task.
 * Contains boolean flag to determine if task has been done.
 */
public class Task {
    public String description;
    public boolean isDone;
    public String type;
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
     * @return time Time of the event or deadline.
     */
    public String getTime() {
        String time = "";
        return time;
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
