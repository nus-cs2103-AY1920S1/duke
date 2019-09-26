package command;

import task.TaskList;
import util.DukeException;
import util.Storage;
import util.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        this.inputCommand = command;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) {
        assert (!inputCommand.isEmpty()) : "Input inputCommand cannot empty";
        if (inputCommand.substring(6).isEmpty()) {
            Ui.deleteEmptyMsg();
        } else {
            try {
                String desc = inputCommand.substring(7);
                int index = Integer.parseInt(desc);
                if (index > taskList.getTasks().size()) {
                    Ui.outOfBoundMsg();
                } else {
                    taskList.deleteTask(index - 1);
                }
            } catch (NumberFormatException e) {
                Ui.invalidNumMsg();
            }
        }
    }
}
