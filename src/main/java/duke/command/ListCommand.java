package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String formattedString = taskList.getTasksInString();
        ui.showMessage(Messages.LIST_MESSAGE, Messages.COMMAND_INDENTATION + formattedString);
    }
}
