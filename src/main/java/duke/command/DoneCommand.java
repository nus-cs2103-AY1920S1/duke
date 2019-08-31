package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;

/**
 * Class representing user's command to mark a Task as done.
 */
public class DoneCommand extends Command {
    private int toComplete;

    /**
     * Class constructor to initialize index of task to mark as completed.
     *
     * @param toComplete Index of Task to complete.
     */
    public DoneCommand(int toComplete) {
        this.toComplete = toComplete;
    }

    /**
     * Executes the Done command.
     *
     * @param taskList List of Tasks to be modified by command.
     * @param ui Ui object to be called by the command.
     * @param storage Storage object to be called by the command.
     */
    public Response execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (toComplete >= taskList.size() || toComplete < 0) {
                throw new DukeException("OOPS! duke.task.Task " + (toComplete + 1) + " doesn't exist!");
            }
            Task curr = taskList.get(toComplete);
            taskList.get(toComplete).setTaskCompleted();
            storage.setChangedTrue();
            return ui.getNiceDoneResponse(curr);
        } catch (DukeException de) {
            return ui.getErrorResponse(de.getMessage());
        }
    }

    /**
     * Returns false as this is not an exit command.
     *
     * @return False as not exit command.
     */
    public boolean isExit() {
        return false;
    }
}
