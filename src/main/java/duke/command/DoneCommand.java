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

    /**
     * Notify the program to exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes Done command.
     * @param taskList TaskList object for the duke program
     * @param ui ui object for the duke program
     * @param storage storage object for the duke program
     * @return true if the command executes successfully, else false
     */
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
