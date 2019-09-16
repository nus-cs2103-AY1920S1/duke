package duke.task;

import duke.command.entities.TaskSorts;
import error.storage.StorageException;
import error.ui.UiException;
import storage.Storage;
import ui.UiController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Controller class that operates on user's task data.
 */
public class TasksController {
    private Storage storage;
    private TasksView view;
    private UiController ui;

    /**
     * TaskListController constructor.
     *
     * @param ui      ui interface for I/O
     * @param storage storage to read and write files
     */
    private TasksController(Storage storage, UiController ui) {
        this.storage = storage;
        this.ui = ui;
        this.view = new TasksView();
    }

    public static TasksController fromStorage(Storage storage, UiController ui) {
        return new TasksController(storage, ui);
    }

    /**
     * Gets tasks.
     *
     * @return list of tasks.
     */
    public List<Task> getTasks() throws StorageException {
        return storage.getTasks();
    }

    /**
     * Adds tasks and prints corresponding feedback.
     *
     * @param task task to be added.
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

    /**
     * Sets a duke.task to done and prints corresponding feedback.
     *
     * @param index index of task to be set to done.
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

    /**
     * Prints all tasks.
     */
    public void displayAllTasks() throws UiException {
        try {
            List<Task> tasks = storage.getTasks();
            view.displayAllTasks(tasks, ui);
        } catch (StorageException e) {
            ui.displayOutput(e.getMessage());
        }
    }

    /**
     * Deletes a task and prints corresponding feedback.
     *
     * @param index index of task to be deleted.
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

    /**
     * Finds tasks containing a substring and prints corresponding feedback.
     *
     * @param parameter substring to be searched.
     */
    public void findTasks(String parameter) throws UiException {
        try {
            String parameterInLowerCase = parameter.toLowerCase();

            List<Task> tasks = storage.getTasks();

            List<Task> matchingTasks = tasks.stream()
                    .filter(task -> task.getDetails().toLowerCase().contains(parameterInLowerCase))
                    .collect(Collectors.toList());

            view.displaySearchResults(matchingTasks, ui);
        } catch (StorageException e) {
            ui.displayOutput(e.getMessage());
        }
    }

    /**
     * Delete a task by its uuid.
     *
     * @param uuid         uuid of task
     * @param printMessage toggles printing of action
     * @throws UiException            if ui fails unexpectedly
     * @throws NoSuchElementException if uuid does not exist
     */
    public void deleteTaskByUuid(UUID uuid, boolean printMessage) throws UiException, NoSuchElementException {
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

    /**
     * Sets a task to undone by its uuid.
     *
     * @param uuid uuid of task
     * @throws UiException            if ui fails unexpectedly
     * @throws NoSuchElementException if uuid does not exist
     */
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

    public void sortTasks(TaskSorts sortingMethod) throws UiException {
        try {
            List<Task> tasks = storage.getTasks();

            tasks.sort(sortingMethod.comparator);

            view.displayTasksSorted(sortingMethod, ui);

            storage.writeTasks(tasks);
        } catch (StorageException e) {
            ui.displayOutput(e.getMessage());
        }
    }

    public void setNewTasksList(List<Task> tasks, boolean printMessage) throws UiException {
        try {
            if (printMessage) {
                view.displayNewTasksSet(tasks.size(), ui);
            }
            storage.writeTasks(tasks);
        } catch (StorageException e) {
            ui.displayOutput(e.getMessage());
        }
    }

    public Optional<Task> replaceTask(Task task, int index) throws UiException {
        try {
            List<Task> tasks = storage.getTasks();

            Task replaced = tasks.remove(index);
            tasks.add(index, task);
            view.displayTaskReplaced(replaced, task, ui);

            assert !tasks.contains(replaced);
            assert tasks.contains(task);

            storage.writeTasks(tasks);

            return Optional.of(replaced);

        } catch (StorageException e) {

            ui.displayOutput(e.getMessage());
            return Optional.empty();

        } catch (IndexOutOfBoundsException e) {
            String message = " ☹ OOPS!!! You have entered an invalid index :-(";

            ui.displayOutput(message);
            return Optional.empty();
        }
    }
}
