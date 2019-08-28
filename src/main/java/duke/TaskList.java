package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves the requested task from the list.
     *
     * @param taskId The 1-based index of the task to be fetched
     * @return The requested task
     * @throws DukeException if invalid taskId is passed in
     */
    public Task get(int taskId) throws DukeException {
        try {
            return tasks.get(taskId - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    String.format("Task No.%d is not present in your list. Please enter a valid task ID.", taskId));
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the requested task from the list.
     *
     * @param taskId The 1-based index of the task to be removed
     * @return The removed task
     * @throws DukeException if invalid taskId is passed in
     */
    public Task remove(int taskId) throws DukeException {
        Task task = get(taskId);
        tasks.remove(task);
        return task;
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Filters the current list of tasks and returns a new task list
     * which only contains tasks with keyword appearing in its description.
     *
     * @param keyWord The keyword to be used for filtering the list of tasks
     * @return a TaskList with all the tasks containing the keyWord
     */
    public TaskList filter(String keyWord) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyWord)) {
                result.add(task);
            }
        }
        return new TaskList(result);
    }
}