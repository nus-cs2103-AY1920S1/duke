package dose.command;

import dose.task.TaskList;
import dose.util.Storage;
import dose.util.Ui;
import dose.util.UiMessage;

public class HelpCommand implements Command {

    /**
     * Shows a list of commands available in the application.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @param storage Object that handles storage of task list to disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(UiMessage.getHelpMessage());
    }
}
