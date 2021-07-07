package command;

import task.TaskList;
import util.Storage;
import util.Ui;

public class ExitCommand extends Command {

    public ExitCommand(String command) {
        this.inputCommand = command;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        storage.save(taskList.getTasks());
        return Ui.byeMsg();
    }
}
