package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Class representing a command to exit Duke.
 */
public class ExitCommand extends Command {
    /**
     * Executes this command on the given task list.
     *
     * @param tl The task list.
     * @param storage The place where tasks will be stored.
     */
    public String execute(TaskList tl, Storage storage) {
        return "Bye!";
    }
}
