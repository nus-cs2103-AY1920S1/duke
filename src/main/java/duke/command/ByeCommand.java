package duke.command;

import duke.TaskList;
import duke.error.DukeException;
import duke.util.Storage;
import duke.util.Ui;

public class ByeCommand implements Command {
    /**
     * Check if exit.
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Execute.
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        ui.printBye();
    }
}
