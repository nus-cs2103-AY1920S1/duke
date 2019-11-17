package duke.command;

import duke.exception.FailedToSaveIoException;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.UserInterface;

/**
 * The <code>ExitCommand</code> is created when the user enters <code>"bye"</code>. The exit command will show the exit
 * message and save all tasks in the list of tasks in the {@link TaskManager} class object to the
 * {@link duke.storage.Storage}. The storage is the file path specified by {@link duke.main.Duke} and
 * {@link duke.storage.Storage}.
 */
public class ExitCommand implements Command {

    /**
     * Executes the command. This will save the list of tasks in {@link TaskManager} into the storage. The storage is
     * the file path specified by {@link duke.main.Duke} and {@link Storage}.
     * @param taskManager the task manager for the tasks
     * @param ui the user interface
     * @param storage the storage for the tasks
     */
    public String execute(TaskManager taskManager, UserInterface ui, Storage storage) {
        try {
            storage.save(taskManager.getCurrentTaskListToSave());
        } catch (FailedToSaveIoException ftsioe) {
            return ui.showSaveError();
        } finally {
            return ui.showExitMessage();
        }
    }

}
