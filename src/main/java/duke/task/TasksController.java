package duke.task;

import duke.command.entities.TaskSorts;
import duke.task.tasks.TasksControllerFeedbackFormatter;
import error.storage.StorageException;
import error.task.TaskAddToModelException;
import error.task.TaskInvalidIndexException;
import error.task.TaskModificationException;
import error.task.TaskNotFoundInModelException;
import error.task.TaskRetrievalFromModelException;
import error.ui.UiException;
import ui.UiOutputAccessor;
import util.ErrorMessageFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Controller task to help mediate the execution of commands that require the retrieval and update of data from the
 * TasksModel. Each method execution will have a user feedback message that will be displayed to the list of registered.
 * A Ui is registered by providing its UiOutputAccessor through the regsiterUi(UiOutputAccessor outputAccessor) method.
 * The TasksController instance will use this accessor to display any necessary feedback in the corresponding Ui.
 */
public class TasksController {
    private TasksControllerFeedbackFormatter feedbackFormatter;
    private TasksModel tasksModel;
    private List<UiOutputAccessor> registeredUis;


    public TasksController(TasksModel tasksModel) {
        this.feedbackFormatter = new TasksControllerFeedbackFormatter();
        this.tasksModel = tasksModel;
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

    private void displayFeedbackInRegisteredUis(String feedbackMessage) throws UiException {
        for (UiOutputAccessor outputChannel : registeredUis) {
            outputChannel.displayOutput(feedbackMessage);
        }
    }

    /**
     * Displays a lists all of the user's tasks in the TasksModel in each of the registered Uis.
     */
    public void listTasks() throws UiException {
        List<Task> tasks;

        try {
            tasks = tasksModel.getCurrentTasks();
        } catch (TaskRetrievalFromModelException e) {
            String feedback = ErrorMessageFormatter.formatErrorMessage("Unable to retrieve tasks.");
            this.displayFeedbackInRegisteredUis(feedback);
            return;
        }

        String feedback = this.feedbackFormatter.displayAllTasks(tasks);
        this.displayFeedbackInRegisteredUis(feedback);
    }

    /**
     * Adds tasks and displays corresponding feedback in each of the registered Uis.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) throws UiException {
        // Try to add task to the model.
        try {
            tasksModel.addTask(task);
        } catch (TaskAddToModelException e) {
            String feedback = ErrorMessageFormatter.formatErrorMessage("Unable to add task.");
            this.displayFeedbackInRegisteredUis(feedback);
            return;
        }

        Optional<Integer> numTasks;

        // Try to get number of tasks after adding the task to the model
        try {
            numTasks = Optional.of(tasksModel.getCurrentTasksCount());
        } catch (TaskRetrievalFromModelException e) {
            numTasks = Optional.empty();
        }

        // Display feedback
        String feedback = this.feedbackFormatter.displayTaskAdded(task, numTasks);
        this.displayFeedbackInRegisteredUis(feedback);
    }

    /**
     * Sets a task to done and prints corresponding feedback in each of the registered Uis..
     *
     * @param index index of task to be set to done.
     */
    public void setTaskToDone(int index) throws UiException {
        // Try to retrieve the task UUID from the index provided and retrieve the corresponding task
        UUID taskUUID;
        Task modifiedTask;
        try {
            taskUUID = tasksModel.getTaskUUIDFromListIndex(index);
            modifiedTask = tasksModel.getTaskFromUuid(taskUUID);
        } catch (TaskRetrievalFromModelException | TaskNotFoundInModelException e) {
            String feedback = ErrorMessageFormatter.formatErrorMessage("Unable to set task to done.");
            this.displayFeedbackInRegisteredUis(feedback);
            return;
        } catch (TaskInvalidIndexException e) {
            String feedback = ErrorMessageFormatter.formatErrorMessage(e.getMessage());
            this.displayFeedbackInRegisteredUis(feedback);
            return;
        }

        // Tries to update the task to done
        try {
            tasksModel.updateTaskDoneStatus(taskUUID, true);
        } catch (TaskNotFoundInModelException e) {
            String feedback = ErrorMessageFormatter.formatErrorMessage(e.getMessage());
            this.displayFeedbackInRegisteredUis(feedback);
            return;
        } catch (TaskModificationException e) {
            String feedback = ErrorMessageFormatter.formatErrorMessage("Task is already set to done.");
            this.displayFeedbackInRegisteredUis(feedback);
            return;
        }

        // Prints corresponding feedback
        String feedback = this.feedbackFormatter.displayTaskSetToDone(modifiedTask);
        this.displayFeedbackInRegisteredUis(feedback);
    }

    /**
     * Deletes a task and displays the corresponding feedback in each of the registered Uis.
     *
     * @param index index of task to be deleted.
     */
    public void deleteTask(int index) throws UiException {
        // Try to retrieve the task UUID from the index provided and retrieve the corresponding task
        UUID taskUUID;
        Task deletedTask;
        try {
            taskUUID = tasksModel.getTaskUUIDFromListIndex(index);
            deletedTask = tasksModel.getTaskFromUuid(taskUUID);
        } catch (TaskRetrievalFromModelException | TaskNotFoundInModelException e) {
            String feedback = ErrorMessageFormatter.formatErrorMessage("Unable to delete task.");
            this.displayFeedbackInRegisteredUis(feedback);
            return;
        } catch (TaskInvalidIndexException e) {
            String feedback = ErrorMessageFormatter.formatErrorMessage(e.getMessage());
            this.displayFeedbackInRegisteredUis(feedback);
            return;
        }

        // Try to delete task from model
        try {
            tasksModel.deleteTask(taskUUID);
        } catch (TaskNotFoundInModelException | TaskModificationException e) {
            String feedback = ErrorMessageFormatter.formatErrorMessage("Unable to delete task.");
            this.displayFeedbackInRegisteredUis(feedback);
            return;
        }

        // Try to get number of tasks after deleting the task from the model
        Optional<Integer> numTasks;
        try {
            numTasks = Optional.of(tasksModel.getCurrentTasksCount());
        } catch (TaskRetrievalFromModelException e) {
            numTasks = Optional.empty();
        }

        // Prints corresponding feedback
        String feedback = this.feedbackFormatter.displayTaskDeleted(deletedTask, numTasks);
        this.displayFeedbackInRegisteredUis(feedback);
    }

    /**
     * Finds tasks containing a substring and displays corresponding feedback in all registered Uis.
     *
     * @param parameter substring to be searched.
     */
    public void findTasks(String parameter) throws UiException {
        // Try to find tasks
        List<Task> matchingTasks;
        try {
            matchingTasks = tasksModel.searchTasks(parameter);
        } catch (TaskRetrievalFromModelException e) {
            String feedback = ErrorMessageFormatter.formatErrorMessage("Unable to retrieve tasks.");
            this.displayFeedbackInRegisteredUis(feedback);
            return;
        }

        // Prints corresponding feedback
        String feedback = this.feedbackFormatter.displayMatchingTasks(matchingTasks);
        this.displayFeedbackInRegisteredUis(feedback);
    }

    public void sortTasks(TaskSorts sortingMethod) throws UiException {
        // Try to sort tasks and print corresponding feedback
        try {
            List<Task> tasks = tasksModel.getCurrentTasks();
            tasks.sort(sortingMethod.comparator);
            tasksModel.setNewTasks(tasks);

            String feedback = this.feedbackFormatter.displayTasksSorted(sortingMethod);
            this.displayFeedbackInRegisteredUis(feedback);

        } catch (TaskRetrievalFromModelException | TaskModificationException e) {
            String feedback = ErrorMessageFormatter.formatErrorMessage("Unable to sort tasks.");
            this.displayFeedbackInRegisteredUis(feedback);
        }
    }

    public void deleteAllTasks() throws UiException {
        //Try to delete tasks and print corresponding feedback
        try {
            tasksModel.deleteALlTasks();

            String feedback = this.feedbackFormatter.displayAllTasksDeleted();
            this.displayFeedbackInRegisteredUis(feedback);
        } catch (TaskModificationException e) {
            String feedback = ErrorMessageFormatter.formatErrorMessage("Unable to delete tasks.");
            this.displayFeedbackInRegisteredUis(feedback);
        }
    }
}
