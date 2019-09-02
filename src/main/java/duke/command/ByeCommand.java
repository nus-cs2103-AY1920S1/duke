package duke.command;

import duke.main.*;

/**
 * Represents the 'bye' command.
 */
public class ByeCommand implements Command {
    public ByeCommand() {
    }

    public void execute(Storage storage, Ui ui, TaskList tasks) {
        ui.sayBye();
    }

    public boolean isRunning() {
        return false;
    }
}
