package duke.task;

import duke.command.entities.TaskSorts;
import duke.task.tasks.TasksControllerFeedback;
import error.task.TaskRepoException;
import error.ui.UiException;
import ui.UiOutputAccessor;
import util.ErrorMessageFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     */
    public void addTask(Task task) throws UiException {
        try {
            tasksRepo.addTask(task);
        } catch (TaskRepoException e) {
            this.displayError(e);
            return;
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
    }

    /**
     * Sets a task to done and prints corresponding feedback in each of the registered Uis..
     *
     * @param index index of task to be set to done.
     */
    public void setTaskToDone(int index) throws UiException {
        try {
            tasksRepo.updateTaskDoneStatus(index, true);
        } catch (TaskRepoException e) {
            this.displayError(e);
            return;
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
    }

    /**
     * Deletes a task and displays the corresponding feedback in each of the registered Uis.
     *
     * @param index index of task to be deleted.
     */
    public void deleteTask(int index) throws UiException {
        Task taskToBeDeleted;

        // Try accessing and deleting task
        try {
            taskToBeDeleted = tasksRepo.getTaskFromListIndex(index);
            tasksRepo.deleteTask(index);
        } catch (TaskRepoException e) {
            this.displayError(e);
            return;
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
     * @param sortingMethod method with which to sort the user's tasks.
     * @throws UiException if the ui fails unexpectedly
     */
    public void sortTasks(TaskSorts sortingMethod) throws UiException {
        // Try to sort tasks and print corresponding feedback
        try {
            List<Task> tasks = tasksRepo.getCurrentTasks();
            tasks.sort(sortingMethod.comparator);
            tasksRepo.setNewTasks(tasks);

            String feedback = this.feedbackFormatter.displayTasksSorted(sortingMethod);
            this.displayFeedback(feedback);
        } catch (TaskRepoException e) {
            this.displayError(e);
        }
    }

    /**
     * Delete all of the user's task data.
     * @throws UiException if the ui fails unexpectedly.
     */
    public void deleteAllTasks() throws UiException {
        //Try to delete tasks and print corresponding feedback
        try {
            tasksRepo.deleteAllTasks();

            String feedback = this.feedbackFormatter.displayAllTasksDeleted();
            this.displayFeedback(feedback);
        } catch (TaskRepoException e) {
            this.displayError(e);
        }
    }
}
