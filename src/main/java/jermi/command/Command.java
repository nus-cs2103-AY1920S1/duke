package jermi.command;

import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.component.Ui;
import jermi.exception.JermiException;

/**
 * Base class for command.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList Task list.
     * @param ui UI.
     * @param storage Storage.
     * @throws JermiException JermiException.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws JermiException;

    /**
     * Indicates if the program should exit.
     *
     * @return {@code true} if should exit, else {@code false}.
     */
    public abstract boolean shouldExit();
}
