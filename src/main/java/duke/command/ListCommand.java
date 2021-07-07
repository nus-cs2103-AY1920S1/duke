package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import java.io.IOException;

/**
 * Executes command to list all tasks in tasklist.
 *
 * @param tasks Current {@link TaskList}
 * @param ui {@link Ui} being used
 * @param storage Current {@link Storage} in use
 * @throws IOException Error with accessing file or {@link java.io.FileWriter}
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showListHeader();
        ui.showList(tasks);
    }

    /**
     * Tests if {@link Command} is {@link ExitCommand}.
     *
     * @return Return true if {@link Command} is {@link ExitCommand}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
