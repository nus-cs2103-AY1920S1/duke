package command;

import task.TaskList;
import util.Storage;
import util.Ui;

public class DoneCommand extends Command {

    public DoneCommand(String command) {
        this.inputCommand = command;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        assert (!inputCommand.isEmpty()) : "Input inputCommand cannot be empty";
        String msg;
        if (inputCommand.substring(4).isEmpty()) {
            msg = Ui.doneErrorMsg();
        } else {
            try {
                String desc = inputCommand.substring(5);
                int index = Integer.parseInt(desc);
                if (index < 1 || index > taskList.getTasks().size()) {
                    msg = Ui.outOfBoundMsg();
                } else {
                    msg = taskList.getTask(index - 1).markAsDone();
                }
            } catch (NumberFormatException e) {
                msg = Ui.invalidNumMsg();
            }
        }
        return msg;

    }
}
