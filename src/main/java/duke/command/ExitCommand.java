package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;

/**
 * Command to end the Duke session.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     *
     * @param tasks Unused.
     * @param storage Unused.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
