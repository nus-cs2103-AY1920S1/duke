package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import static duke.ui.Messages.FIND_NO_TASKS;
import static duke.ui.Messages.FIND_TASKS;
import static duke.ui.Messages.LIST_NO_TASKS;
import static duke.ui.Messages.LIST_TASKS;
import static duke.ui.Messages.TASKS_COUNT;
import static duke.ui.Messages.TASK_ADD_SUCCESS;
import static duke.ui.Messages.TASK_DELETED;
import static duke.ui.Messages.TASK_MARKED_AS_DONE;

public interface Ui {
    void showMessage(final String message);

    void showWarning(final String warning);

    void showError(final String error);

    void showWelcome();

    void showBye();

    /**
     * Inform user that a task has been added successfully.
     *
     * @param task  the task that has been added
     * @param tasks all the tasks after the addition
     */
    default void addTaskSuccess(final Task task, final TaskList tasks) {
        showMessage(
            String.format("%s%n%s%n%s",
                TASK_ADD_SUCCESS,
                task.toString(),
                String.format(TASKS_COUNT, tasks.size())));
    }

    /**
     * Inform user that a task has been deleted successfully.
     *
     * @param task  the task that has been deleted
     * @param tasks all the tasks after the deletion
     */
    default void deleteTaskSuccess(final Task task, final TaskList tasks) {
        showMessage(
            String.format("%s%n%s%n%s",
                TASK_DELETED,
                task.toString(),
                String.format(TASKS_COUNT, tasks.size())));
    }

    default void taskMarkedAsDone(final Task task) {
        showMessage(String.format("%s%n%s", TASK_MARKED_AS_DONE, task.toString()));
    }

    default void findNoMatch() {
        showMessage(FIND_NO_TASKS);
    }

    default void findMatches(final TaskList matches) {
        showMessage(String.format("%s%n%s", FIND_TASKS, matches.toString()));
    }

    default void listNoTasks() {
        showMessage(LIST_NO_TASKS);
    }

    default void listTasks(TaskList tasks) {
        showMessage(String.format("%s%n%s", LIST_TASKS, tasks.toString()));
    }
}
