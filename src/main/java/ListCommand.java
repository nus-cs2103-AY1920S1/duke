import java.io.IOException;

public class ListCommand extends Command {

    public ListCommand() {
        super("list");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getNumTasks() == 0) {
            ui.showMessage("You have no tasks yet!");
        } else {
            ui.showListCommandResponse(taskList.getTaskArr());
        }
    }

    /**
     * To help with debugging.
     */
    public void print() {
        super.print();
    }
}