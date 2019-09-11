package duke.tasks;

import java.util.Collections;
import java.util.Comparator;
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
        return "\nNow you have " + allTasks.size()
                + (allTasks.size() == 1 ? " task in the list." : " tasks in the list.");
    }

    /**
     * Sorts the LinkedList containing all existing tasks by name.
     *
     * @return The sorted LinkedList.
     */
    public LinkedList<Task> sortByName() {
        LinkedList<Task> copy = new LinkedList<>(allTasks);
        Comparator<Task> nameSorter = Comparator.comparing(Task::getDescription);
        copy.sort(nameSorter);
        return copy;
    }

    /**
     * Sorts the LinkedList containing all existing tasks by date.
     *
     * @return The sorted LinkedList.
     */
    public LinkedList<Task> sortByDate() {
        LinkedList<Task> copy = new LinkedList<>(allTasks);
        Comparator<Task> nameSorter = Comparator.comparing(Task::getDate,
                Comparator.nullsFirst(Comparator.naturalOrder()));
        copy.sort(nameSorter);
        return copy;
    }
}
