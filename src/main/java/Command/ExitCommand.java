package command;

import task.TaskList;
import util.Storage;
import util.Ui;

public class ExitCommand extends Command {

    public ExitCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) {
        isExit = true;
        Ui.byeMsg();
    }
}
