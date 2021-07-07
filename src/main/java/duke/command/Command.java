package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Encapsulates a command.
 */
public abstract class Command {
    /**
     * Constructs an Command object.
     */
    Command() {

    }
    /**
     * Abstract method to execute command, to be overridden by child classes.
     * @param duke Chat bot.
     * @param storage  Data file of chat bot.
     * @param taskList  Task list of chat bot.
     * @return Result of command.
     * @throws DukeException  If data file pointed to by storage cannot be updated.
     */

    public abstract String execute(Duke duke, Storage storage, TaskList taskList) throws DukeException;

    /**
     * Flags command as not a command to end chat bot.
     *
     * @return False, indicating that command is not a command to end chat bot.
     */
    public boolean isBye() {
        return false;
    }
}

