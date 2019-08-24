package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private int itemNum;
    public DoneCommand(int itemNum) {
        super();
        this.itemNum = itemNum;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.getTask(itemNum - 1).completeTask();
            ui.showMessage(Messages.DONE_MESSAGE, Messages.COMMAND_INDENTATION +
                    Messages.COMPLETION_INDENTATION + taskList.getTask(itemNum - 1).toString());
            return true;
        } catch (IndexOutOfBoundsException e) {
            ui.showError(Messages.INVALID_SIZE_EXCEPTION);
            return false;
        }
    }
}
