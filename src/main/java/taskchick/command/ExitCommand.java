package taskchick.command;

import taskchick.tasklist.TaskList;
import taskchick.storage.Storage;

/**
 * Command to end the Task Chick session.
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
