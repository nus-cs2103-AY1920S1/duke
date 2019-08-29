package seedu.duke.commands;

import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
