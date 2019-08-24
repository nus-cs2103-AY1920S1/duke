package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

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
