package myduke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import myduke.exception.DukeInvalidCommandException;

/**
 * Manages a list of tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Deletes the specified task from the list and returns it.
     * @param index The task based on an index which starts from 1.
     * @return The task that was deleted.
     * @throws DukeInvalidCommandException If the task does not exist.
     */
    public Task deleteTask(int index) throws DukeInvalidCommandException {
        if (index <= 0 || this.size() < index) {
            throw new DukeInvalidCommandException("No such task was found");
        }

        return this.remove(index - 1);
    }

    /**
     * Gets a task based on an index which starts from 1.
     * @param index The task based on an index which starts from 1.
     * @return The task based on index.
     * @throws DukeInvalidCommandException If the task does not exist.
     */
    public Task getTask(int index) throws DukeInvalidCommandException {
        if (index <= 0 || this.size() < index) {
            throw new DukeInvalidCommandException("No such task was found");
        }

        return this.get(index - 1);
    }

    /**
     * Marks a specified task as done.
     * @param index The task based on an index which starts from 1.
     * @return The task that was marked as done.
     * @throws DukeInvalidCommandException If the task does not exist.
     */
    public Task markTaskAsDone(int index) throws DukeInvalidCommandException {
        Task refTask = getTask(index);
        refTask.markAsDone();
        return refTask;
    }

    /**
     * Filters the tasks which contains the keyword.
     * @param keyword the key search term.
     * @return A list of filtered tasks
     */
    public TaskList filterTasks(String keyword) {
        TaskList newList = new TaskList();
        this.stream()
                .filter(task -> task.description.toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(() -> newList));

        return newList;
    }
}
