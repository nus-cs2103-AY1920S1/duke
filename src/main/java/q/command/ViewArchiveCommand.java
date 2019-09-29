package q.command;

import q.error.FileLoadError;
import q.main.Storage;
import q.main.TaskList;
import q.main.Ui;

/**
 * Handles viewing of the archive.
 */
public class ViewArchiveCommand extends Command {
    /**
     * Shows the archive.
     * @param tasks   The main TaskList.
     * @param ui      The User Interface being used.
     * @param storage The Storage being used.
     * @return A String showing the archive.
     * @throws FileLoadError If the archive cannot be loaded.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FileLoadError {
        return ui.showArchived(storage.getArchive());
    }
}
