import java.util.HashMap;

/**
 * Represents the command of displaying Duke's list of help commands.
 */
public class HelpCommand extends Command {

    /**
     * Executes the display of Duke's list of help commands.
     *
     * @param tasks TaskList object to add the task to.
     * @param ui Duke object's Ui object to display add task message.
     * @param taskStorage Duke object's Storage object to access file for loading/saving tasks.
     * @param archiveStorage Duke object's ArchiveStorage object to access archives file for loading/saving archives.
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     * @return String list of help commands
     */
    @Override
    public String execute(TaskList tasks, Ui ui, TaskStorage taskStorage, ArchiveStorage archiveStorage,
                          HashMap<String, TaskList> archives) {
        return ui.showHelpMessage();
    }
}
