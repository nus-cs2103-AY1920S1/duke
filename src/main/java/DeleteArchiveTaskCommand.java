import java.util.HashMap;

/**
 * Represents the command of deletion a task from an archive in a Duke object's HashMap of archive Tasklists.
 */
public class DeleteArchiveTaskCommand extends ArchiveCommand {
    private String archiveName;
    private int taskNumber;

    /**
     * Creates a DeleteArchiveTaskCommand object.
     *
     * @param taskNumber Index of task in archive to be deleted.
     * @param archiveName Name of archive to delete task from.
     */
    public DeleteArchiveTaskCommand(int taskNumber, String archiveName) {
        this.archiveName = archiveName;
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the deletion of a task from an archive.
     * Returns the delete archive task message.
     *
     * @param tasks TaskList object to add the task to.
     * @param ui Duke object's Ui object to display add task message.
     * @param taskStorage Duke object's TaskStorage object to access tasks file for loading/saving normal tasks.
     * @param archiveStorage Duke object's ArchiveStorage object to access archives file for loading/saving archives.
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     * @return String delete archive task message.
     * @throws InvalidCommandDukeException Thrown when the archive name or task index entered is invalid.
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

        TaskList archive = archives.get(archiveName);

        if (taskNumber >= 0 && taskNumber < archive.taskListSize()) {
            Task removedTask = archive.getTask(taskNumber);
            archive.deleteTask(taskNumber);
            if (archive.taskListSize() == 0) {
                archives.remove(archiveName);
            }

            archiveStorage.loadArchivedTasksToFile(archives);
            return ui.showDeleteArchiveTaskMessage(removedTask, archiveName);
        } else {
            throw new InvalidCommandDukeException("OOF!! There is no task labelled that number!!");
        }
    }
}
