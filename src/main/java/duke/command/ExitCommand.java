package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

public class ExitCommand extends Command {

    /**
     * Executes the Exit Command
     *
     * @param tasks The current tasks loaded
     * @param storage The storage file loaded
     * @param command The command that is being executed
     */
    @Override
    public String execute(TaskList tasks, Storage storage, String command) throws IOException, DukeException {
        isExit = true;
        storage.save(tasks.getList());
        return Ui.showBye();
    }
}
