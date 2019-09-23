package duke.logic;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a TaskList in Duke.
 */
public class TaskList {
    /**
     * List of all tasks.
     */
    private List<Task> list;

    /**
     * Empty constructor of TaskList. Creates an empty list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Creates a TaskList from a List of Tasks.
     *
     * @param list List of Tasks.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Get list from TaskList.
     *
     * @return List containing Tasks.
     */
    public List<Task> getList() {
        return list;
    }

    /**
     * Adds Task to TaskList.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Removes Task from TaskList.
     *
     * @param position Position to remove from.
     * @return Removed Task.
     */
    public Task remove(int position) {
        Task deleted = list.remove(position - 1);
        return deleted;
    }

    /**
     * Get Task from TaskList.
     *
     * @param position Position to retrieve from.
     * @return Task retrieved.
     */
    public Task get(int position) {
        return this.list.get(position - 1);
    }

    /**
     * Returns size of TaskList.
     *
     * @return int value of size of TaskList.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns true if TaskList is empty.
     *
     * @return boolean value of whether TaskList is empty.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Marks Task in TaskList (in given position) as done.
     *
     * @param position Position of Task to mark as done.
     */
    public Task markAsDone(int position) {
        Task task = this.get(position);
        task.done();
        return task;
    }
}
