import java.util.HashMap;

/**
 * Represents the command of listing all Task objects from a Duke object's TaskList object.
 */
public class ListCommand extends Command {

    /**
     * Executes the displaying of all Task objects from a Duke object's TaskList object.
     *
     * @param tasks TaskList object with tasks to be displayed.
     * @param ui Duke object's Ui object to display the tasks.
     * @param taskStorage Duke object's Storage object to access file for loading/saving tasks.
     * @param archiveStorage Duke object's ArchiveStorage object to access archives file for loading/saving archives.
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     * @return String list of all tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, TaskStorage taskStorage, ArchiveStorage archiveStorage,
                          HashMap<String, TaskList> archives) {
        return ui.showListMessage(tasks);
    }
}
