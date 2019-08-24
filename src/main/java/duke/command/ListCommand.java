package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    /**
     * Notify the program to exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes List command.
     * @param taskList TaskList object for the duke program
     * @param ui ui object for the duke program
     * @param storage storage object for the duke program
     * @return true
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        String formattedString = taskList.getTasksInString();
        ui.showMessage(Messages.LIST_MESSAGE, Messages.COMMAND_INDENTATION + formattedString);
        return true;
    }
}
