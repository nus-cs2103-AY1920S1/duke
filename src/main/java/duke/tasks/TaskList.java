package duke.tasks;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TaskList {
    private static LinkedList<Task> allTasks;

    /**
     * Initialises a TaskList with allTasks.
     *
     * @param allTasks The LinkedList containing all existing tasks.
     */
    public TaskList(LinkedList<Task> allTasks) {
        TaskList.allTasks = allTasks;
    }

    /**
     * Initialises an empty TaskList.
     */
    public TaskList() {
        allTasks = new LinkedList<>();
    }

    /**
     * Gets the LinkedList from TaskList.
     *
     * @return The LinkedList containing all existing tasks.
     */
    public List<Task> getList() {
        return Collections.unmodifiableList(allTasks);
    }

    /**
     * Adds the task to the LinkedList containing all existing tasks.
     *
     * @param t The task to be newly added.
     */
    public void addTask(Task t) {
        allTasks.add(t);
    }

    /**
     * Deletes the task from the LinkedList containing all existing tasks.
     *
     * @param t The task to be deleted.
     */
    public void deleteTask(Task t) {
        allTasks.remove(t);
    }

    /**
     * Gets the current status of task list.
     *
     * @return The string representation of its status.
     */
    public String getStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNow you have ")
          .append(allTasks.size());
        if (allTasks.size() == 1) {
            sb.append(" task in the list.");
        } else {
            sb.append(" tasks in the list.");
        }
        return sb.toString();
    }
}
