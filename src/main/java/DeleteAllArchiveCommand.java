import java.util.HashMap;

/**
 * Represents the command of deletion an entire archive of tasks from a Duke object's HashMap of archive Tasklists.
 */
public class DeleteAllArchiveCommand extends ArchiveCommand {
    private String archiveName;

    /**
     * Creates a DeleteAllArchiveCommand object.
     *
     * @param archiveName Name of archive to be deleted.
     */
    public DeleteAllArchiveCommand(String archiveName) {
        this.archiveName = archiveName;
    }

    /**
     * Executes the deletion of an entire archive and its tasks.
     * Returns the delete entire archive message.
     *
     * @param tasks TaskList object to add the task to.
     * @param ui Duke object's Ui object to display add task message.
     * @param taskStorage Duke object's TaskStorage object to access tasks file for loading/saving normal tasks.
     * @param archiveStorage Duke object's ArchiveStorage object to access archives file for loading/saving archives.
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     * @return String delete entire archive message.
     * @throws InvalidCommandDukeException Thrown when the archive name entered is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, TaskStorage taskStorage, ArchiveStorage archiveStorage,
                          HashMap<String, TaskList> archives) throws InvalidCommandDukeException {
        if (!archives.containsKey(archiveName)) {
            throw new InvalidCommandDukeException("OOF!! There isn't an existing archive with the name " + archiveName);
        }

        if (archiveName.trim().equals("")) {
            throw new InvalidCommandDukeException("OOF!! Please enter an archive name!!");
        }

        archives.remove(archiveName);
        archiveStorage.loadArchivedTasksToFile(archives);
        return ui.showDeleteAllArchiveMessage(archiveName, archives);
    }
}
