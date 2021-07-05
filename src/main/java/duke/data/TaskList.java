package duke.data;

import duke.data.tasks.Task;

import java.util.ArrayList;

import java.util.stream.Stream;

/**
 * Implements a list for storing tasks.
 * @author Lim Yong Shen, Kevin
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds the specified task to the task list.
     * @param task The specified task.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns true if this task list is empty.
     * @return true if this task list is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the task at the specified index of this task list.
     * @param index The specified index.
     * @return The task at the specified index of this task list.
     * @throws IndexOutOfBoundsException If the specified index is out of bounds.
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Removes and returns the task at the specified index of this task list.
     * @param index The specified index.
     * @return The task at the specified index of this task list.
     * @throws IndexOutOfBoundsException If the specified index is out of bounds.
     */
    public Task remove(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    /**
     * Returns the size of this task list.
     * @return The size of this task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a sub task list withs tasks containing the specified keyword.
     * @param keyword The specified keyword.
     * @return A sub task list with tasks containing the specified keyword.
     */
    public TaskList subTaskListContainingKeyWord(String keyword) {
        TaskList subTaskList = new TaskList();
        Stream<Task> allTasks = tasks.stream();
        allTasks.filter(task -> task.getDescription().contains(keyword))
                .forEach(task -> subTaskList.add(task));
        return subTaskList;
    }

}
