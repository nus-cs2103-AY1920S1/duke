package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Messages.BYE_MESSAGE;

public class ByeCommand extends Command {
    @Override
    protected void check(TaskList tasks) {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(BYE_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
