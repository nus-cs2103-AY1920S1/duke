package command;

import task.TaskList;
import util.Storage;
import util.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        this.inputCommand = command;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        assert (!inputCommand.isEmpty()) : "Input inputCommand cannot empty";
        String msg;
        if (inputCommand.substring(6).isEmpty()) {
            msg = Ui.deleteEmptyMsg();
        } else {
            try {
                String desc = inputCommand.substring(7);
                int index = Integer.parseInt(desc);
                if (index > taskList.getTasks().size()) {
                    msg = Ui.outOfBoundMsg();
                } else {
                    msg = taskList.deleteTask(index - 1);
                }
            } catch (NumberFormatException e) {
                msg = Ui.invalidNumMsg();
            }
        }
        return msg;
    }
}
