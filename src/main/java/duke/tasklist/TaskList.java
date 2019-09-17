package duke.tasklist;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Task List.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Initialises with given list of tasks.
     *
     * @param tl to do list
     */
    public TaskList(List<Task> tl) {
        tasks = tl;
    }

    /**
     * Initialises with empty list of task.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Sets task at specified index of To Do List as done and returns it (one based numbering).
     *
     * @param i Index
     * @return Task at specified index.
     */
    public String done(int i) {
        tasks.get(i - 1).setDone(true);
        return tasks.get(i - 1).toString();
    }

    /**
     * Delete index of To Do List. (one based numbering)
     *
     * @param i Index
     * @return Deleted task
     */
    public String delete(int i) {
        String taskMessage = tasks.get(i - 1).toString();
        tasks.remove(i - 1);
        return taskMessage;
    }

    /**
     * Appends task to To Do List. (one based numbering)
     *
     * @param t task
     * @return message to print
     */
    public String addTask(Task t) {
        tasks.add(t);
        return tasks.get(tasks.size() - 1).toString();
    }

    /**
     * Get Task size.
     *
     * @return task size
     */
    public int getTasksSize() {
        return tasks.size();
    }

    /**
     * Get tasks as a string.
     *
     * @return tasks
     */
    public String getTasks() {
        StringJoiner result = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            result.add(String.format("%d.%s", i + 1, t));
        }

        return result.toString();
    }

    /**
     * Finds tasks based on keyword.
     * @param kw keyword
     * @return tasks
     */
    public String find(String kw) {
        StringJoiner result = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.toString().contains(kw)) {
                result.add(String.format("%d.%s", i + 1, t));
            }
        }

        return result.toString();
    }
}