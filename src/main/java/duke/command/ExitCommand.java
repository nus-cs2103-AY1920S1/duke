package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents ExitCommand which causes Duke to close.
 */
public class ExitCommand extends Command {
    /**
     * Constructor for ExitCommand.
     */
    public ExitCommand() {
    }

    /**
     * Executes ExitCommand. Saves current tasks to disk and says goodbye.
     * @param tasks Retrieves Tasks from TaskList.
     * @param ui Performs actions on Ui if required.
     * @param storage Saves Tasks from TaskList to Storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveList(tasks);
        ui.sayGoodbye();
    }

    /**
     * Returns false as it is an ExitCommand.
     * @return Boolean value of whether Duke should continue running.
     */
    @Override
    public boolean isRunning() {
        return false;
    }
}
