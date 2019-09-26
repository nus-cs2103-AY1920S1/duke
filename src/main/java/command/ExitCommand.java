package command;

import task.TaskList;
import util.DukeException;
import util.Storage;
import util.Ui;

import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand(String command) {
        this.inputCommand = command;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) throws DukeException, IOException {
        setExit(true);
        storage.save(taskList.getTasks());
        Ui.byeMsg();
    }
}
