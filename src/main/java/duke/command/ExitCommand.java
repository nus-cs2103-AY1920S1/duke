package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String command) throws IOException, DukeException {
        isExit = true;
        storage.save(tasks.getList());
        Ui.showBye();
    }
}
