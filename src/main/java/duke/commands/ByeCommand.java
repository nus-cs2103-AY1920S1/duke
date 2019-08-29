package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class ByeCommand extends Command {
    public void execute(Ui ui, Storage storage, TaskList allTasks) throws DukeException {
        this.isExit = true;
        super.execute(ui, storage, allTasks);
    }
}
