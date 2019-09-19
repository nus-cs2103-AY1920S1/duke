package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.ui.Ui;

/**
 * Abstraction of the Bye Command.
 * eg: add [some task]
 */
public class ByeCommand extends Command {

    /**
     * Executes the exit procedure.
     * {@code tasks} are saved to disk before exiting.
     * @param tasks The TaskList to save to storage.
     */
    @Override
    public String execute(TaskList tasks) {
        return "\t" + "Bye. Hope to see you again soon!";
    }
}

