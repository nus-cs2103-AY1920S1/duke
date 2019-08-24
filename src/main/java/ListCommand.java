import java.util.List;

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
