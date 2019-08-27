package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * @param storage  Data file of chat bot.
     * @param taskList  Task list of chat bot.
     * @param ui  User interface of chat bot.
     * @throws DukeException  If data file pointed to by storage cannot be updated.
     */

    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException;

    /**
     * Flags command as not a command to end chat bot.
     *
     * @return False, indicating that command is not a command to end chat bot.
     */
    public boolean isBye() {
        return false;
    }
}

