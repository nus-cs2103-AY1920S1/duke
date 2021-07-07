package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import java.io.IOException;

public class ExitCommand extends Command {
    /**
     * Exits the program and saves list into current storage.
     *
     * @param tasks Current {@link TaskList}
     * @param ui {@link Ui} being used
     * @param storage Current {@link Storage} in use
     * @throws IOException Error with accessing file or {@link java.io.FileWriter}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.close();
        ui.showClosingMessage();
    }

    /**
     * Tests if {@link Command} is {@link ExitCommand}.
     *
     * @return Return true if {@link Command} is {@link ExitCommand}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
