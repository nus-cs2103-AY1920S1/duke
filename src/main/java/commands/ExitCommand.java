package commands;

import exceptions.DukeException;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

import java.util.List;

public class ExitCommand extends Command {

    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.writeToFile();
        ui.exitMessage();
    }
}
