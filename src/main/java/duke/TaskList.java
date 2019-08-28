package duke;

import java.util.ArrayList;
import duke.task.Task;
import duke.exception.DukeException;

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
     * Retrieves the requested <code>Task</code> object from the list.
     *
     * @param taskId The 1-based index of the task to be fetched
     * @return The requested <code>Task</code> Object
     * @throws DukeException when invalid taskId is passed in
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
     * Removes the requested <code>Task</code> object from the list.
     *
     * @param taskId The 1-based index of the task to be removed
     * @return The removed <code>Task</code> object
     * @throws DukeException when invalid taskId is passed in
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
}