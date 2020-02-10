package czkay.duke.logic.command;

import czkay.duke.model.TaskList;
import czkay.duke.storage.Storage;

/**
 * A Command to exit the Duke program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit the Duke program.
     *
     * @param tasks The task list.
     * @param storage The storage that handles saving and loading the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

}
