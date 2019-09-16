package command;

import task.TaskList;
import util.DukeException;
import util.Storage;
import util.Ui;

import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) throws DukeException, IOException {
        isExit = true;
        storage.save(taskList.getTasks());
        Ui.byeMsg();
    }
}
