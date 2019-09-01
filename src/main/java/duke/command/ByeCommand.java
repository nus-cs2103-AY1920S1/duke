package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Performs a Bye command
 * by executing the Ui to show bye screen.
 */
public class ByeCommand extends Command {

    public ByeCommand(String command, Task pending) {
        super(command, pending);
    }

    /**
     * Shows Ui screen of bye.
     *
     * @param list    List containing all tasks.
     * @param ui      Ui interface of duke.
     * @param storage Storage interface.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Return boolean indicating if command is exit command.
     *
     * @return boolean flag indicating if is exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
