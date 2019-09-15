package seedu.duke.commands.base;

import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

public class TriviaStartCommand extends Command {

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return ui.startTrivia();
    }
}
