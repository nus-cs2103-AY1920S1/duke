package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * An abstract class of commands.
 * Each instance of its sub-classes are instances of commands.
 */
public abstract class Command {
    /** Whether the command is an exit command. */
    protected boolean isExit;

    /**
     * Acts as intended depending on the command.
     *
     * @param taskList TaskList of tasks.
     * @param ui Ui object for user interaction.
     * @param storage Storage object for data file.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Checks if command is an exit command.
     *
     * @return boolean attribute of whether command is an exit command.
     */
    public boolean isExit() {
        return isExit;
    }
}