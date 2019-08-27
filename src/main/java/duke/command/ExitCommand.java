package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        Ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
