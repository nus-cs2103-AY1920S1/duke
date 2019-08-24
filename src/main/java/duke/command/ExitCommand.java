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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.saveData(taskList.getTaskList());
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
        ui.showMessage(Messages.BYE_MESSAGE);
    }
}
