package duke.task;

import error.storage.StorageException;
import error.ui.UiException;
import storage.Storage;
import ui.UiController;

import java.util.*;
import java.util.stream.Collectors;

/***
 * <p>
 * Controller class that operates on user's duke.task data.
 * </p>
 */
public class TasksController {
    private Storage storage;
    private TasksView view;
    private UiController ui;

    /***
     * <p>
     * TaskListController constructor.
     * </p>
     */
    private TasksController(Storage storage, UiController ui) {
        this.storage = storage;
        this.ui = ui;
        this.view = new TasksView();
    }

    public static TasksController fromStorage(Storage storage, UiController ui) {
        return new TasksController(storage, ui);
    }

    /***
     * <p>
     * Gets tasks.
     * </p>
     * @return list of tasks.
     */
    public List<Task> getTasks() throws UiException {
        try {
            return storage.getTasks();
        } catch (StorageException e) {
            ui.displayOutput(e.getMessage());
            return new ArrayList<>();
        }
    }

    /***
     * <p>
     * Adds tasks and prints corresponding feedback.
     * </p>
     * @param task duke.task to be added.
     */
    public void addTask(Task task, boolean displayMessage) throws UiException {
        try {
            List<Task> tasks = storage.getTasks();
            tasks.add(task);

            assert tasks.contains(task);

            storage.writeTasks(tasks);

            if (displayMessage) {
                view.displayNewTask(task, tasks.size(), ui);
            }
        } catch (StorageException e) {
            ui.displayOutput(e.getMessage());
        }
    }

    /***
     * <p>
     * Sets a duke.task to done and prints corresponding feedback.
     * </p>
     * @param index index of duke.task to be set to done.
     */
    public Optional<Task> setTaskToDone(int index) throws UiException {
        try {
            List<Task> tasks = storage.getTasks();
            Task done = tasks.get(index);
            done.setDone(true);

            view.displayTaskDone(tasks.get(index), ui);

            assert tasks.get(index).isDone();

            // write changes to storage file
            storage.writeTasks(tasks);

            return Optional.of(done);

        } catch (StorageException e) {
            ui.displayOutput(e.getMessage());
            return Optional.empty();
        }
    }

    /***
     * <p>
     * Prints all tasks.
     * </p>
     */
    public void displayAllTasks() throws UiException {
        try {
            List<Task> tasks = storage.getTasks();
            view.displayAllTasks(tasks, ui);
        } catch (StorageException e) {
            ui.displayOutput(e.getMessage());
        }
    }

    /***
     * <p>
     * Deletes a duke.task and prints corresponding feedback.
     * </p>
     * @param index index of duke.task to be deleted.
     */
    public Optional<Task> deleteTask(int index) throws UiException {
        try {
            List<Task> tasks = storage.getTasks();

            Task deleted = tasks.get(index);
            tasks.remove(index);
            view.displayTaskDeleted(deleted, tasks.size(), ui);

            assert !tasks.contains(deleted);

            storage.writeTasks(tasks);

            return Optional.of(deleted);
        } catch (StorageException e) {
            ui.displayOutput(e.getMessage());
            return Optional.empty();
        } catch (IndexOutOfBoundsException e) {
            String message = " ☹ OOPS!!! You have entered an invalid index :-(";

            ui.displayOutput(message);
            return Optional.empty();
        }
    }

    /***
     * <p>
     * Finds tasks containing a substring and prints corresponding feedback.
     * </p>
     * @param parameter substring to be searched.
     */
    public void findTasks(String parameter) throws UiException {
        try {
            List<Task> tasks = storage.getTasks();

            List<Task> matchingTasks = tasks.stream()
                    .filter(task -> task.getDetails().contains(parameter))
                    .collect(Collectors.toList());

            view.displaySearchResults(matchingTasks, ui);
        } catch (StorageException e) {
            ui.displayOutput(e.getMessage());
        }
    }

    public void deleteTaskByUuid (UUID uuid, boolean printMessage) throws UiException, NoSuchElementException {
        try {
            List<Task> tasks = storage.getTasks();

            Task deleted = tasks.stream()
                    .filter(task -> task.getUuid().equals(uuid))
                    .findFirst()
                    .get();

            tasks.remove(deleted);

            if (printMessage) {
                view.displayTaskDeleted(deleted, tasks.size(), ui);
            }

            storage.writeTasks(tasks);
        } catch (StorageException e) {
            ui.displayOutput(e.getMessage());
        }
    }

    public void setTaskToUndoneByUuid(UUID uuid) throws UiException, NoSuchElementException {
        try {
            List<Task> tasks = storage.getTasks();

            Task undone = tasks.stream()
                    .filter(task -> task.getUuid().equals(uuid))
                    .findFirst().get();

            undone.setDone(false);
            view.displayTaskUndone(undone, ui);

            storage.writeTasks(tasks);
        } catch (StorageException e) {
            ui.displayOutput(e.getMessage());
        }
    }
}
