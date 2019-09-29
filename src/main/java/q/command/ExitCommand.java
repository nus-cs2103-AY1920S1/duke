package q.command;

import q.main.Storage;
import q.main.TaskList;
import q.main.Ui;

/**
 * Handles exiting of the program.
 */
public class ExitCommand extends Command {
    /**
     * Checks if this command is an exit command.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Exits the program.
     *
     * @param tasks   The main TaskList.
     * @param ui      The User Interface being used.
     * @param storage The Storage being used.
     * @return A String showing bye.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }
}
