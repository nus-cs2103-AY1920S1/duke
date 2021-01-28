package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;

/**
 * Class representing user's command to list all Tasks currently tracked by Duke.
 */
public class ListCommand extends Command {

    /**
     * Returns false as this is not an exit command.
     *
     * @return False as not exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to list all tasks and returns Duke's response.
     *
     * @param taskList List of Tasks to be modified by command.
     * @param ui Ui object to be called by the command.
     * @param storage Storage object to be called by the command.
     * @return Response to be sent to the GUI.
     */
    public Response execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.getPrintTaskResponse(taskList);
    }
}
