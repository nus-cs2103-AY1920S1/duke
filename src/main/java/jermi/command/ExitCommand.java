package jermi.command;

import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.component.Ui;
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
     * @param ui UI.
     * @param storage Storage.
     * @return Output response.
     * @throws JermiException JermiException.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws JermiException {
        return ui.exit();
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
}
