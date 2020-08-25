package seedu.duke.commands.base;

import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

/**
 * Command that ends the application.
 */
public class ByeCommand extends Command {

    /**
     * Displays message to user to say goodbye.
     *
     * @param tasks Not applicable.
     * @param ui UI to say goodbye to the user.
     * @param storage Not applicable.
     * @return String that says goodbye to the user.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return ui.bye();
    }

    /**
     * Triggers flag to exit application.
     *
     * @return true to exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
