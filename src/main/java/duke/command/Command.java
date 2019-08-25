package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Command Abstract Class. Abstract class for sub-commands (add, delete, do, exit, list).
 */
public abstract class Command {
    /** Whether the command is an exit command. */
    protected boolean isExit;

    /**
     * Command behaviour. Implemented in subclasses.
     * @param taskList TaskList of tasks.
     * @param ui Ui object for user interaction.
     * @param storage Storage object for data file.
     */
    abstract public void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Checks if command is an exit command.
     * @return isExit boolean attribute.
     */
    public boolean isExit() {
        return isExit;
    }
}