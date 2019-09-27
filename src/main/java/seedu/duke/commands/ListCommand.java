package seedu.duke.commands;

import seedu.duke.storage.TaskList;
import seedu.duke.ui.StringStore;

/**
 * Abstraction of the List Command.
 * eg: list
 */
public class ListCommand extends Command {

    /**
     * Lists all the tasks in {@code tasks} in a 1-Index based format.
     * @param tasks The current TaskList instance.
     */
    @Override
    public String execute(TaskList tasks) {
        return StringStore.LIST_SUCCESSFUL + tasks.getListAsString();
    }
}
