package command;

import task.TaskList;
import util.Storage;

public class ClearCommand extends Command {

    public ClearCommand(String command) {
        this.inputCommand = command;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return taskList.clearTasks();
    }
}
