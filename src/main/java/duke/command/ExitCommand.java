package duke.command;

import duke.logic.TaskList;
import duke.logic.Storage;

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
     *
     * @param tasks   Retrieves Tasks from TaskList.
     * @param storage Saves Tasks from TaskList to Storage.
     * @return String representation of executed command.
     */
    public String execute(TaskList tasks, Storage storage) {
        storage.saveList(tasks);
        return "Bye. Hope to see you again soon!";
    }
}
