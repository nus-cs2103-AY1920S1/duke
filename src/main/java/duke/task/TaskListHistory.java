package duke.task;

import java.util.ArrayList;

public class TaskListHistory {
    private static TaskListHistory history = null;
    private ArrayList<TaskList> tasklists;

    private TaskListHistory() {
        this.tasklists = new ArrayList<>();
    }

    /**
     * Returns the singleton TaskListHistory instance.
     * 
     * @see <a href=
     *      "https://nus-cs2103-ay1920s1.github.io/website/se-book-adapted/chapters/designPatterns.html#singleton-pattern">
     *      Textbook reference on Singleton Object</a>
     * 
     * @return the TaskListHistory instance.
     */
    public static TaskListHistory getInstance() {
        if (history == null) {
            history = new TaskListHistory();
        }
        return history;
    }

    /**
     * Adds a tasklist snapshot to the history.
     * 
     * @param tasks the tasklist snapshot to save
     */
    public void push(TaskList tasks) {
        tasklists.add(tasks.clone());
    }

    /**
     * Pops a tasklist snapshot. Returns null if history is empty.
     * 
     * @return a Tasklist, or null if history is empty
     */
    public TaskList pop() {
        if (tasklists.size() == 0) {
            return null;
        }

        return tasklists.remove(tasklists.size() - 1);
    }
}