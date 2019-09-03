package duke.command;

import duke.storage.Storage;
import duke.logic.TaskList;
import duke.ui.Ui;

/**
 * Bye Command Object.
 */

public class ByeCommand extends Command {

    public ByeCommand() {
        super(true);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ByeCommand)) {
            return false;
        }

        return true;

    }
}
