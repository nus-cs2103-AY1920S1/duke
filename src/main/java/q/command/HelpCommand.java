package q.command;

import q.main.Storage;
import q.main.TaskList;
import q.main.Ui;

/**
 * Handles help.
 */
public class HelpCommand extends Command {
    /**
     * Provides a String of help descriptors.
     *
     * @param tasks   The main TaskList.
     * @param ui      The User Interface being used.
     * @param storage The Storage being used.
     * @return A String with help descriptors.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelp();
    }
}
