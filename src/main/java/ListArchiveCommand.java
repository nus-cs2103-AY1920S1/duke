import java.util.HashMap;

/**
 * Represents the command of listing the names of all archives in a Duke object's HashMap of archive Tasklists.
 */
public class ListArchiveCommand extends ArchiveCommand {

    /**
     * Executes the listing the names of all archives.
     * Returns the list archive message.
     *
     * @param tasks TaskList object to add the task to.
     * @param ui Duke object's Ui object to display add task message.
     * @param taskStorage Duke object's TaskStorage object to access tasks file for loading/saving normal tasks.
     * @param archiveStorage Duke object's ArchiveStorage object to access archives file for loading/saving archives.
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     * @return String list archive message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, TaskStorage taskStorage, ArchiveStorage archiveStorage,
                          HashMap<String, TaskList> archives) {
        return ui.showListArchiveMessage(archives);
    }
}
