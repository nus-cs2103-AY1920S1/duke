package jermi.command;

import static jermi.misc.Constant.EXIT_COMMAND;

import java.util.Objects;

import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.component.Formatter;
import jermi.exception.JermiException;

/**
 * A representation of the command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Public constructor for class.
     */
    public ExitCommand() {
        super();
    }

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
        return formatter.exit();
    }

    /**
     * Indicates if the program should exit.
     *
     * @return {@code true}.
     */
    @Override
    public boolean shouldExit() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(EXIT_COMMAND);
    }
}
