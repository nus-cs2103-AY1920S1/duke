package duke.task;

import duke.command.sort.TaskSorts;
import error.task.TaskRepoException;
import error.ui.UiException;
import ui.UiOutputAccessor;
import util.strings.ErrorMessageFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Controller task to help mediate the execution of commands that require the retrieval and update of data from the
 * TasksModel. Each method execution will have a user feedback message that will be displayed to the list of registered.
 * A Ui is registered by providing its UiOutputAccessor through the regsiterUi(UiOutputAccessor outputAccessor) method.
 * The TasksController instance will use this accessor to display any necessary feedback in the corresponding Ui.
 */
public class TasksController {
    private TasksControllerFeedback feedbackFormatter;
    private ITaskRepo tasksRepo;
    private List<UiOutputAccessor> registeredUis;


    public TasksController(ITaskRepo tasksRepo) {
        this.feedbackFormatter = new TasksControllerFeedback();
        this.tasksRepo = tasksRepo;
        this.registeredUis = new ArrayList<>();
    }

    /**
     * Method used by the program to register any Uis to listen to any commands executed by the TasksController. Each
     * command executed by the TasksController will result in the display of a feedback message to all the registered
     * Uis through its UiOutputAccessor.
     *
     * @param uiOutputAccessor the uiOutputAccessor of the ui to be registered.
     */
    public void registerUi(UiOutputAccessor uiOutputAccessor) {
        this.registeredUis.add(uiOutputAccessor);
    }

    private void displayFeedback(String feedbackMessage) throws UiException {
        for (UiOutputAccessor outputChannel : registeredUis) {
            outputChannel.displayOutput(feedbackMessage);
        }
    }

    private void displayError(TaskRepoException e) throws UiException {
        this.displayFeedback(ErrorMessageFormatter.formatErrorMessage(e.getMessage()));
    }

    private void displayError(String e) throws UiException {
        this.displayFeedback(ErrorMessageFormatter.formatErrorMessage(e));
    }

    /**
     * Displays a lists all of the user's tasks in the TasksModel in each of the registered Uis.
     */
    public void listTasks() throws UiException {
        try {
            List<Task> tasks = tasksRepo.getCurrentTasks();
            String feedback = this.feedbackFormatter.displayAllTasks(tasks);
            this.displayFeedback(feedback);
        } catch (TaskRepoException e) {
            this.displayError(e);
        }
    }

    /**
     * Adds tasks and displays corresponding feedback in each of the registered Uis.
     *
     * @param task the task to be added.
     * @return true if the task was added successfully.
     */
    public boolean addTask(Task task) throws UiException {
        try {
            tasksRepo.addTask(task);
        } catch (TaskRepoException e) {
            this.displayError(e);
            return false;
        }

        // Try to get number of tasks after adding the task to the model
        Optional<Integer> numTasks;
        try {
            numTasks = Optional.of(tasksRepo.getCurrentTasksCount());
        } catch (TaskRepoException e) {
            numTasks = Optional.empty();
        }

        // Display feedback
        String feedback = this.feedbackFormatter.displayTaskAdded(task, numTasks);
        this.displayFeedback(feedback);
        return true;
    }

    public boolean addTaskToIndex(int index, Task task) throws UiException {
        try {
            tasksRepo.addTask(task);
        } catch (TaskRepoException e) {
            this.displayError(e);
            return false;
        }

        // Try to get number of tasks after adding the task to the model
        Optional<Integer> numTasks;
        try {
            numTasks = Optional.of(tasksRepo.getCurrentTasksCount());
        } catch (TaskRepoException e) {
            numTasks = Optional.empty();
        }

        // Display feedback
        String feedback = this.feedbackFormatter.displayTaskAdded(task, numTasks);
        this.displayFeedback(feedback);
        return true;
    }

    /**
     * Sets a new list of tasks as the user's current tasks. The old task information will be lost.
     *
     * @param tasks the new list of tasks.
     * @return true if the new tasks were successfully set.
     * @throws UiException if the ui fails unexpectedly.
     */
    public boolean setNewTasks(List<Task> tasks) throws UiException {
        try {
            tasksRepo.setNewTasks(tasks);
        } catch (TaskRepoException e) {
            this.displayError(e);
            return false;
        }

        String feedback = this.feedbackFormatter.displayAllTasks(tasks);
        this.displayFeedback(feedback);
        return true;
    }

    /**
     * Sets a task to done and prints corresponding feedback in each of the registered Uis.
     *
     * @param index index of task to be set to done.
     * @return true if the task was modified successfully.
     */
    public boolean setTaskToDone(int index) throws UiException {
        try {
            tasksRepo.updateTaskDoneStatus(index, true);
        } catch (TaskRepoException e) {
            this.displayError(e);
            return false;
        }

        // Try to get modified task
        Optional<Task> modifiedTask;
        try {
            modifiedTask = Optional.of(tasksRepo.getTaskFromListIndex(index));
        } catch (TaskRepoException e) {
            modifiedTask = Optional.empty();
        }
        // Prints corresponding feedback
        String feedback = this.feedbackFormatter.displayTaskSetToDone(modifiedTask);
        this.displayFeedback(feedback);
        return true;
    }

    /**
     * Sets a task to undone and prints corresponding feedback in each of the registered Uis.
     *
     * @param index index of task to be set to done.
     * @return true if the task was modified successfully.
     */
    public boolean setTaskToUndone(int index) throws UiException {
        try {
            tasksRepo.updateTaskDoneStatus(index, false);
        } catch (TaskRepoException e) {
            this.displayError(e);
            return false;
        }

        // Try to get modified task
        Optional<Task> modifiedTask;
        try {
            modifiedTask = Optional.of(tasksRepo.getTaskFromListIndex(index));
        } catch (TaskRepoException e) {
            modifiedTask = Optional.empty();
        }

        // Prints corresponding feedback
        String feedback = this.feedbackFormatter.displayTaskSetToUndone(modifiedTask);
        this.displayFeedback(feedback);
        return true;
    }

    /**
     * Deletes a task and displays the corresponding feedback in each of the registered Uis.
     *
     * @param index index of task to be deleted.
     * @return the deleted task or null if it fails to be deleted.
     */
    public Task deleteTask(int index) throws UiException {
        Task taskToBeDeleted;

        // Try accessing and deleting task
        try {
            taskToBeDeleted = tasksRepo.getTaskFromListIndex(index);
            tasksRepo.deleteTask(index);
        } catch (TaskRepoException e) {
            this.displayError(e);
            return null;
        }

        // Try getting size of new list
        Optional<Integer> numTasks;
        try {
            numTasks = Optional.of(tasksRepo.getCurrentTasksCount());
        } catch (TaskRepoException e) {
            numTasks = Optional.empty();
        }

        String feedback = this.feedbackFormatter.displayTaskDeleted(taskToBeDeleted, numTasks);
        this.displayFeedback(feedback);
        return taskToBeDeleted;
    }

    /**
     * Finds tasks containing a substring and displays corresponding feedback in all registered Uis.
     *
     * @param parameter substring to be searched.
     */
    public void findTasks(String parameter) throws UiException {
        // Try to find tasks
        try {
            List<Task> matchingTasks = tasksRepo.searchTasks(parameter);
            String feedback = this.feedbackFormatter.displayMatchingTasks(matchingTasks);
            this.displayFeedback(feedback);
        } catch (TaskRepoException e) {
            this.displayError(e);
        }
    }

    /**
     * Sorts tasks according to the specified sorting method.
     *
     * @param sortingMethod method with which to sort the user's tasks.
     * @return old tasks if successful or null if sort was unsuccessful
     * @throws UiException if the ui fails unexpectedly
     */
    public List<Task> sortTasks(TaskSorts sortingMethod) throws UiException {
        // Try to sort tasks and print corresponding feedback
        try {
            List<Task> oldTasks = tasksRepo.getCurrentTasks();

            List<Task> sortedTasks = tasksRepo.getCurrentTasks();
            sortedTasks.sort(sortingMethod.comparator);
            this.setNewTasks(sortedTasks);

            String feedback = this.feedbackFormatter.displayTasksSorted(sortingMethod);
            this.displayFeedback(feedback);

            return oldTasks;
        } catch (TaskRepoException e) {
            this.displayError(e);
            return null;
        }
    }

    /**
     * Delete all of the user's task data.
     *
     * @return the previous lists of tasks or null if it fails to delete.
     * @throws UiException if the ui fails unexpectedly.
     */
    public List<Task> deleteAllTasks() throws UiException {
        //Try to delete tasks and print corresponding feedback
        try {
            List<Task> oldTasks = tasksRepo.getCurrentTasks();

            tasksRepo.deleteAllTasks();

            String feedback = this.feedbackFormatter.displayAllTasksDeleted();
            this.displayFeedback(feedback);
            return oldTasks;
        } catch (TaskRepoException e) {
            this.displayError(e);
            return null;
        }
    }

    /**
     * Delete a particular task by its uuid.
     *
     * @param uuid the uuid of the task to be deleted.
     * @return the deleted task or null if it fails to be deleted..
     * @throws UiException if the ui fails unexpectedly.
     */
    public Task deleteTaskByUuid(UUID uuid) throws UiException {
        Task taskToBeDeleted;

        // Try accessing and deleting task
        try {
            Optional<Task> optionalTask = tasksRepo.getCurrentTasks().stream()
                    .filter(task -> task.getUuid().equals(uuid)).findFirst();

            if (optionalTask.isEmpty()) {
                this.displayError("Task is not found.");
                return null;
            }

            taskToBeDeleted = optionalTask.get();

            List<Task> newTasks = tasksRepo.getCurrentTasks().stream()
                    .filter(task -> !task.getUuid().equals(uuid))
                    .collect(Collectors.toList());

            this.tasksRepo.setNewTasks(newTasks);

        } catch (TaskRepoException e) {
            this.displayError(e);
            return null;
        }

        // Try getting size of new list
        Optional<Integer> numTasks;
        try {
            numTasks = Optional.of(tasksRepo.getCurrentTasksCount());
        } catch (TaskRepoException e) {
            numTasks = Optional.empty();
        }

        String feedback = this.feedbackFormatter.displayTaskDeleted(taskToBeDeleted, numTasks);
        this.displayFeedback(feedback);
        return taskToBeDeleted;
    }

    /**
     * Replaces the task at a particular index with a new task.
     * @param index the index of the task to be replaced.
     * @param newTask the task to be replaced with.
     * @return the old task if successful or null if not.
     */
    public Task setTask(int index, Task newTask) throws UiException {
        Task taskToBeReplaced;

        try {
            taskToBeReplaced = this.tasksRepo.getTaskFromListIndex(index);

            this.tasksRepo.updateTask(index, newTask);
        } catch (TaskRepoException e) {
            this.displayError("Unable to update task details.");
            return null;
        }

        String feedback = this.feedbackFormatter.displayTaskReplaced(newTask);
        this.displayFeedback(feedback);

        return taskToBeReplaced;
    }
}
