package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of tasks.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> myList) {
        this.taskList = myList;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added to the list of tasks.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the task list at a given index.
     *
     * @param index The index of the task to be deleted in the task list.
     * @return The task that was deleted.
     * @throws DukeException if there is no task at the given index.
     */
    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("Task does not exist");
        }
        return taskList.remove(index);
    }

    /**
     * Returns a task from the task list at a given index.
     *
     * @param index The index of the task in the task list which should be retrieved.
     * @return The task at the given index in the task list.
     * @throws DukeException If there is no task at the given index.
     */
    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("Task does not exist");
        }
        return taskList.get(index);
    }

    /**
     * Gets the number of tasks in the list of tasks.
     *
     * @return The number of tasks in the list of tasks.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets the taskList for future use.
     *
     * @return The taskList.
     */
    public List<Task> getTaskList() {
        return taskList;
    }
}
