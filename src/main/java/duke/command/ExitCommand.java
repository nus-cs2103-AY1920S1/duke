package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.saveTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
