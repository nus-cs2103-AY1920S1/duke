package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;

/**
 * Abstract class that represents a command given by the User for Duke to execute.
 */
public abstract class Command {

    /**
     * Executes the command that is intended by the user.
     *
     * @param taskList List of Tasks to be modified by command.
     * @param ui Ui object to be called by the command.
     * @param storage Storage object to be called by the command.
     *
     * @return Message to be output
     */
    public abstract Response execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns true if the command is to exit Duke.
     *
     * @return True or false depending if command is a ByeCommand.
     */
    public abstract boolean isExit();
}
