package command;

import exception.DukeException;
import filewriter.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Abstract class which all other command classes extend. (AddCommand etc...) with the exception of FulLCommand enum.
 */
public abstract class Command {
    FullCommand type;

    /**
     * Checks to see if type equals FullCommand.BYE.
     * @return boolean value whether this is an Exit command.
     */
    public boolean isExit() {
        return type.getName().equals("bye");
    }

    /**
     * Carries out the command.
     * @param tasks current TaskList object used in this instance of Duke..
     * @param ui Instance of user interface to print feedback to user.
     * @param storage updates data record of TaskList in storage.filepath if needed.
     * @throws DukeException subclass of Command may throw DukeException when executed with invalid specification.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
