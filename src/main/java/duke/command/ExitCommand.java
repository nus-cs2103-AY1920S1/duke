package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class ExitCommand extends Command {

    /**
     * Notify the program to exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes Exit command.
     * @param taskList TaskList object for the duke program
     * @param ui ui object for the duke program
     * @param storage storage object for the duke program
     * @return true if the command executes successfully, else false
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.saveData(taskList.getTaskList());
            ui.showMessage(Messages.BYE_MESSAGE);
            return true;
        } catch (IOException e) {
            ui.showError(e.getMessage());
            return false;
        }

    }
}
