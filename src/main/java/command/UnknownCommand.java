package command;

import task.TaskList;
import util.Storage;
import util.Ui;

public class UnknownCommand extends Command {

    public UnknownCommand(String command) {
        this.inputCommand = command;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return Ui.unknownMsg();
    }
}
