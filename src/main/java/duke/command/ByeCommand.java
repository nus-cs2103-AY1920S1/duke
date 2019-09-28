package duke.command;

import duke.main.Storage;
import duke.main.Ui;
import duke.main.TaskList;

/**
 * Represents the 'bye' command.
 */
public class ByeCommand implements Command {
    public String execute(Storage storage, Ui ui, TaskList tasks) {
        return ui.getClosing();
    }
}
