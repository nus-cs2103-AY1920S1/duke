package jermi.command;

import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.component.Formatter;
import jermi.exception.JermiException;

/**
 * Base class for command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList Task list.
     * @param formatter Formatter.
     * @param storage Storage.
     * @return Output response.
     * @throws JermiException JermiException.
     */
    public abstract String execute(TaskList taskList, Formatter formatter, Storage storage) throws JermiException;

    /**
     * Indicates if the program should exit.
     *
     * @return {@code true} if should exit, else {@code false}.
     */
    public abstract boolean shouldExit();
}
