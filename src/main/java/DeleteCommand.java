import java.util.List;

public class DeleteCommand extends Command {

    private int itemNum;
    public DeleteCommand(int itemNum) {
        super();
        this.itemNum = itemNum;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task removedTask = taskList.deleteFromTaskList(itemNum - 1);
            ui.showMessage( Messages.DELETE_TASK_MESSAGE,
                    Messages.COMMAND_INDENTATION + Messages.COMPLETION_INDENTATION + removedTask.toString(),
                    String.format(Messages.LIST_SIZE_FORMAT, taskList.getSize()));
        } catch (IndexOutOfBoundsException e) {
            ui.showError(Messages.INVALID_SIZE_EXCEPTION);
        }
    }
}
