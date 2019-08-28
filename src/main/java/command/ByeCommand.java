package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to end the Duke program.
 *
 */
public class ByeCommand extends Command {

    public ByeCommand() {
        this.setExit(true);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        ui.printStatement("Bye. Hope to see you again soon!");
    }
}
