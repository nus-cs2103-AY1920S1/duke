package command;

import task.TaskList;
import util.Storage;
import util.Ui;

public class UnknownCommand extends Command {

    public UnknownCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) {
        Ui.unknownMsg();
    }
}
