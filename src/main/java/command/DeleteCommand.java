package command;

import task.TaskList;
import util.Storage;
import util.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) {
        if (command.substring(6).isEmpty()) {
            Ui.deleteEmptyMsg();
        } else {
            String desc = command.substring(7);
            int index = Integer.parseInt(desc);
            if (index > taskList.getTasks().size()) {
                Ui.outOfBoundMsg();
            } else {
                taskList.deleteTask(index - 1);
            }
        }
    }
}
