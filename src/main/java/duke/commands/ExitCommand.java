package duke.commands;

import duke.tasks.TaskList;
import duke.Storage;
import duke.ui.Ui;

public class ExitCommand extends Command {
    /**
     * Exit the program.
     * @param taskList task list of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
    }

    /**
     * Check whether the command is an exit command.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
