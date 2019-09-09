package duke.task;

import duke.task.tasks.Task;
import error.StorageException;
import storage.Storage;

import java.util.List;
import java.util.stream.Collectors;

/***
 * <p>
 * Controller class that operates on user's duke.task data.
 * </p>
 */
public class TasksController {
    private Storage storage;
    private TasksView view;

    /***
     * <p>
     * TaskListController constructor.
     * </p>
     * @param view list of tasks.
     */
    private TasksController(Storage storage, TasksView view) {
        this.storage = storage;
        this.view = view;
    }

    public static TasksController fromStorage(Storage storage, TasksView view) {
        return new TasksController(storage, view);
    }

    /***
     * <p>
     * Gets tasks.
     * </p>
     * @return list of tasks.
     */
    public List<Task> getTasks() throws StorageException {
        try {
            return storage.getTasks();
        } catch (StorageException e) {
            view.displayError(e);
            throw e;
        }
    }

    /***
     * <p>
     * Adds tasks and prints corresponding feedback.
     * </p>
     * @param task duke.task to be added.
     */
    public void addTask(Task task) {
        try {
            List<Task> tasks = storage.getTasks();
            tasks.add(task);
            storage.writeTasks(tasks);
        } catch (StorageException e) {
            view.displayError(e);
        }
    }

    /***
     * <p>
     * Sets a duke.task to done and prints corresponding feedback.
     * </p>
     * @param index index of duke.task to be set to done.
     */
    public void setTaskToDone(int index) {
        try {
            List<Task> tasks = storage.getTasks();
            tasks.get(index).setDone(true);

            view.displayTaskDone(tasks.get(index));

            // write changes to storage file
            storage.writeTasks(tasks);
        } catch (StorageException e) {
            view.displayError(e);
        }
    }

    /***
     * <p>
     * Prints all tasks.
     * </p>
     */
    public void displayAllTasks() {
        try {
            List<Task> tasks = storage.getTasks();
            view.displayAllTasks(tasks);

        } catch (StorageException e) {
            view.displayError(e);
        }
    }

    /***
     * <p>
     * Deletes a duke.task and prints corresponding feedback.
     * </p>
     * @param index index of duke.task to be deleted.
     */
    public void deleteTask(int index) {
        try {
            List<Task> tasks = storage.getTasks();

            Task deleted = tasks.get(index);
            tasks.remove(index);
            view.displayTaskDeleted(deleted, tasks.size());

            storage.writeTasks(tasks);
        } catch (StorageException | IndexOutOfBoundsException e) {
            view.displayError(e);
        }
    }

    /***
     * <p>
     * Finds tasks containing a substring and prints corresponding feedback.
     * </p>
     * @param parameter substring to be searched.
     */
    public void findTasks(String parameter) {
        try {
            List<Task> tasks = storage.getTasks();

            List<Task> matchingTasks = tasks.stream()
                    .filter(task -> task.getDescription().contains(parameter))
                    .collect(Collectors.toList());

            view.displaySearchResults(matchingTasks);

        } catch (StorageException e) {
            view.displayError(e);
        }
    }
}
