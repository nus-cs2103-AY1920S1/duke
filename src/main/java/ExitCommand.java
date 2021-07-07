import java.util.HashMap;

/**
 * Represents the command of exiting a Duke object run.
 * Contains the method calls to execute this command.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit from a Duke object's run process.
     * Task objects are saved into the Duke object's filepath and
     * the exit message is returned.
     *
     * @param tasks TaskList object of the Duke object.
     * @param ui Duke object's Ui object to display the tasks.
     * @param taskStorage Duke object's Storage object to access file for loading/saving tasks.
     * @param archiveStorage Duke object's ArchiveStorage object to access archives file for loading/saving archives.
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     * @return String exit message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, TaskStorage taskStorage, ArchiveStorage archiveStorage,
                          HashMap<String, TaskList> archives) {
        taskStorage.loadTasksToFile(tasks);
        archiveStorage.loadArchivedTasksToFile(archives);
        return ui.showByeMessage();
    }

    /**
     * Indicates the exit condition of the running Duke object.
     *
     * @return To stop the Duke object from running, returns true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
