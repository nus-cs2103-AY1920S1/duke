package duke.command;

import duke.task.TaskList;
import duke.exception.*;
import duke.storage.Storage;
import duke.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printBye();
        super.isExit = true;
    }
}