package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private int itemNum;
    public DeleteCommand(int itemNum) {
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
     * Executes Delete command.
     * @param taskList TaskList object for the duke program
     * @param ui ui object for the duke program
     * @param storage storage object for the duke program
     * @return true if the command executes successfully, else false
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task removedTask = taskList.deleteFromTaskList(itemNum - 1);
            ui.showMessage( Messages.DELETE_TASK_MESSAGE,
                    Messages.COMMAND_INDENTATION + Messages.COMPLETION_INDENTATION + removedTask.toString(),
                    String.format(Messages.LIST_SIZE_FORMAT, taskList.getSize()));
            return true;
        } catch (IndexOutOfBoundsException e) {
            ui.showError(Messages.INVALID_SIZE_EXCEPTION);
            return false;
        }
    }
}
