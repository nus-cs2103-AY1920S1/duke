package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.InvalidCommandException;

/**
 * Inherits from abstract Command class.
 * Handles execution of commands that are not recognised by Duke
 */
public class InvalidCommand extends Command {

    public InvalidCommand() {
    }

    /**
     * Executes commands that are not recognized by Duke
     *
     * @param tasks   <code>TaskList</code> object which holds the taskList
     *                and various methods to operate on the taskList
     * @param ui      <code>UI</code> object which handles console output
     * @param storage <code>Storage</code> object which allows for reading
     *                result of executed command into preset task.txt file
     * @throws InvalidCommandException whenever this overridden method is called.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws InvalidCommandException {
        throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
