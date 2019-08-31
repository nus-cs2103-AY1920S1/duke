package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;

/**
 * Class to represent command to exit Duke.
 */
public class ByeCommand extends Command {

    /**
     * Returns true because this is an exit command.
     *
     * @return True indicating this is a command to exit Duke.
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command to exit Duke.
     *
     * @param taskList List of Tasks to be modified by command.
     * @param ui Ui object to be called by the command.
     * @param storage Storage object to be called by the command.
     */
    public Response execute(TaskList taskList, Ui ui, Storage storage) {
        ui.closeInput();
        Response toReturn = ui.getGoodByeResponse();
        if (storage.storageUpdated()) {
            try {
                storage.writeToDisk(taskList);
                toReturn = ui.getDoneWritingAndByeResponse();
            } catch (DukeException de) {
                return ui.getWritingErrorAndByeResponse();
            }
        }
        return toReturn;
    }
}
