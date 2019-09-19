package seedu.duke.commands;

import seedu.duke.exceptions.InvalidArgumentException;
import seedu.duke.storage.TaskList;
import seedu.duke.ui.Ui;

/**
 * Abstraction of the List Command.
 * eg: list
 */
public class ListCommand extends Command {

    /**
     * Lists all the tasks in {@code tasks} in a 1-Index based list.
     * @param tasks The current TaskList instance.
     */
    @Override
    public String execute(TaskList tasks) {

        return tasks.printAll();
    }
}
