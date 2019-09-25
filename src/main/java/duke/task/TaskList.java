package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class manages the task list and has operations to add/delete/modify tasks in the list.
 *
 * @author scwaterbear
 */
public class TaskList {

    /**
     * List of tasks for the user.
     */
    private List<Task> tasks;

    /**
     * Class constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Class constructor that loads existing tasks.
     *
     * @param tasks list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a list of tasks which descriptions match the keyword.
     *
     * @param keyword the search term.
     * @return List Found tasks.
     */
    public List<Task> getTasksWithKeywords(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.hasKeywordsInDescription(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param t task to add.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Modifies task status to done.
     *
     * @param index task identifier.
     * @return Task the task marked as done.
     * @throws DukeException If there is no such task in the list.
     */
    public Task setIsDone(int index) throws IndexOutOfBoundsException {
            tasks.get(index).isDone = true;
            return tasks.get(index);
    }

    /**
     * Removes the specified task from the task list.
     *
     * @param index task identifier.
     * @return Task the task removed.
     * @throws DukeException If there is no such task in the list.
     */
    public Task removeTask(int index) throws IndexOutOfBoundsException {
            return tasks.remove(index);
    }

    /**
     * Returns all tasks in the task list.
     *
     * @return List list of all tasks.
     */
    public List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns size of task list.
     *
     * @return int size of task list.
     */
    public int getSize() {
        return tasks.size();
    }
}
