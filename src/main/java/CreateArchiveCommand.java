import java.util.HashMap;

/**
 * Represents the command of creating a new archive by adding a new TaskList object to a Duke objects's
 * HashMap of archive TaskLists.
 */
public class CreateArchiveCommand extends ArchiveCommand {

    private String archiveName;

    /**
     * Creates a CreateArchiveCommand object.
     *
     * @param archiveName Name of new archive to be created.
     */
    public CreateArchiveCommand(String archiveName) {
        this.archiveName = archiveName;
    }

    /**
     * Executes the creation of a new archive.
     * Returns the create new archive message.
     *
     * @param tasks TaskList object to add the task to.
     * @param ui Duke object's Ui object to display add task message.
     * @param taskStorage Duke object's TaskStorage object to access tasks file for loading/saving normal tasks.
     * @param archiveStorage Duke object's ArchiveStorage object to access archives file for loading/saving archives.
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     * @return String create new archive message.
     * @throws InvalidCommandDukeException Thrown when the archive name entered is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, TaskStorage taskStorage, ArchiveStorage archiveStorage,
                          HashMap<String, TaskList> archives) throws InvalidCommandDukeException {

        if (archiveStorage.isEndOfArchiveMarker(archiveName) || archiveName.trim().equals("")) {
            throw new InvalidCommandDukeException("OOF!! Invalid archive name entered, please choose another name!!");
        }

        if (archives.containsKey(archiveName)) {
            throw new InvalidCommandDukeException("OOF!! There is already an existing archive with the same name!!");
        }

        archives.put(archiveName, new TaskList());
        archiveStorage.loadArchivedTasksToFile(archives);
        return ui.showCreateArchiveMessage(archiveName);
    }
}
