import java.util.HashMap;

/**
 * Represents the command of viewing all lists of tasks in every archive in a Duke object's HashMap of archive Tasklists.
 */
public class ViewAllArchiveCommand extends ArchiveCommand {

    /**
     * Executes the display of all lists of tasks in every archive in a Duke object's HashMap of archive Tasklists.
     * Returns the view all archive message.
     *
     * @param tasks TaskList object to add the task to.
     * @param ui Duke object's Ui object to display add task message.
     * @param taskStorage Duke object's TaskStorage object to access tasks file for loading/saving normal tasks.
     * @param archiveStorage Duke object's ArchiveStorage object to access archives file for loading/saving archives.
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     * @return String view all archive message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, TaskStorage taskStorage, ArchiveStorage archiveStorage,
                          HashMap<String, TaskList> archives) {
        return ui.showViewAllArchiveMessage(archives);
    }
}
