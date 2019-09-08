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
     * @return String representation of executed command.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveList(tasks);
        return "Bye. Hope to see you again soon!";
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
