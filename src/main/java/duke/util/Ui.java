package duke.util;

import duke.task.TaskList;
import duke.util.exception.DukeException;

/**
 * Encapsulates methods related to displaying responses from Duke in the UI.
 */
public interface Ui {
    /**
     * Returns the command entered by the user.
     * @return Command entered by the user.
     */
    String readCommand();

    /**
     * Displays the required message in the UI, given the type of message required.
     * @param uiMessage Enum indicating type of message required to be displayed.
     */
    void showMessage(UiMessage uiMessage);

    /**
     * Displays the required message in the UI, given the message required as a String.
     * @param message String representing message to be displayed.
     */
    void showMessage(String message);

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
