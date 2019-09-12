package jermi.command;

import static jermi.misc.Constant.COMMAND_CLEAR_MESSAGE;

import jermi.component.Formatter;
import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.exception.JermiException;

/**
 * A representation of the command for deleting all the tasks from the list.
 */
public class ClearCommand extends Command {
    /**
     * Executes the command.
     *
     * @param taskList Task list.
     * @param formatter Formatter.
     * @param storage Storage.
     * @return Output response.
     * @throws JermiException JermiException.
     */
    @Override
    public String execute(TaskList taskList, Formatter formatter, Storage storage) throws JermiException {
        taskList.clear();
        storage.taskListToFile();
        return formatter.echo(COMMAND_CLEAR_MESSAGE);
    }

    /**
     * Indicates if the program should exit.
     *
     * @return {@code false}.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
