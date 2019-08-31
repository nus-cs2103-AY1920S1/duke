package commands;

import exceptions.DukeException;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

import java.util.List;

public class ExitCommand extends Command {

    /**
     * Relay to the driver class that the user wishes to exit Duke.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Write the new task list to file and give feedback to the user
     * upon successful exit from Duke.
     *
     * @param tasks the object that contains the current list of tasks
     * @param ui to give feedback to the user
     * @param storage enables writing to file
     * @throws DukeException if there are any problems writing to file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.writeToFile();
        ui.exitMessage();
    }
}
