package q.command;

import q.main.Storage;
import q.main.TaskList;
import q.main.Ui;

/**
 * Handles archiving.
 */
public class ArchiveCommand extends Command {
    /**
     * Archives all tasks.
     *
     * @param tasks   The main TaskList.
     * @param ui      The User Interface being used.
     * @param storage The Storage being used.
     * @return A String representing Tasks that have been archived.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String result = ui.showArchived(tasks);
        storage.archive(tasks);
        return result;
    }
}
