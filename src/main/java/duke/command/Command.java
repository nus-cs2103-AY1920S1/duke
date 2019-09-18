package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command which has specific uses.
 *
 * @see TaskList
 * @see Task
 * @see AddCommand
 * @see ByeCommand
 * @see DeleteCommand
 * @see DoneCommand
 * @see FindCommand
 * @see ListCommand
 */

public abstract class Command {
    final String[] commandSplitBySpaces;

    /**
     * Constructor for all commands.
     *
     * @param commandSplitBySpaces String representation of the user input
     */
    Command(String[] commandSplitBySpaces) {
        this.commandSplitBySpaces = commandSplitBySpaces;
    }

    /**
     * Executes the command by using the three arguments provided and
     * Returns the String to be written.
     *
     * @param taskList taskList used to store tasks
     * @param ui User Interface
     * @param storage Storage
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Checks if duke.Duke will end.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
