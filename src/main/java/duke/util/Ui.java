package duke.util;

import duke.task.TaskList;
import duke.util.exception.DukeException;

public interface Ui {
    /**
     * Displays the required message in the UI, given the type of message required.
     * @param uiMessage Enum indicating type of message required to be displayed.
     */
    void showMessage(UiMessage uiMessage);

    /**
     * Displays the required error message in the UI, given the type of exception thrown.
     * @param exception Exception thrown by the application.
     */
    void showError(DukeException exception);

    /**
     * Displays a list of tasks in the UI.
     * @param tasks Tasks to be shown in the UI.
     */
    void showTasks(TaskList tasks);
}
