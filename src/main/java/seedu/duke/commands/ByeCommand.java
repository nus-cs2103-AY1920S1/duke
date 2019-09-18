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
     * @throws Storage.StorageOperationException occurs when there is an issue saving the tasklist to disk
     */
    @Override
    public void execute(TaskList tasks) throws Storage.StorageOperationException {
        Ui.printMessages("\t" + "Bye. Hope to see you again soon!");
        try {
            Storage.getInstance().saveToDisk(tasks);
        } catch (Storage.StorageOperationException e) {
            throw e;
        }
        System.exit(0);
    }
}

