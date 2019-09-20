import java.util.HashMap;

/**
 * Represents the command of adding a Task object from a Duke object's lists of tasks to an archive.
 */
public class AddArchiveTaskCommand extends ArchiveCommand {
    private int taskNumber;
    private String archiveName;

    /**
     * Creates an AddArchiveTaskCommand object.
     *
     * @param taskNumber Index of task object to be archived.
     * @param archiveName Name of archive to add task into.
     */
    public AddArchiveTaskCommand(int taskNumber, String archiveName) {
        this.taskNumber = taskNumber;
        this.archiveName = archiveName;
    }

    /**
     * Executes the adding of a Task object from a Duke object's lists of tasks to an archive.
     * Returns the add task to archive message.
     *
     * @param tasks TaskList object to add the task to.
     * @param ui Duke object's Ui object to display add task message.
     * @param taskStorage Duke object's TaskStorage object to access tasks file for loading/saving normal tasks.
     * @param archiveStorage Duke object's ArchiveStorage object to access archives file for loading/saving archives.
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     * @return String add task to archive message.
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

        if (taskNumber >= 0 && taskNumber < tasks.taskListSize()) {
            Task archivedTask = tasks.getTask(taskNumber);
            tasks.deleteTask(taskNumber);
            TaskList archive = archives.get(archiveName);
            archive.addTask(archivedTask);
            archiveStorage.loadArchivedTasksToFile(archives);
            return ui.showAddArchiveTaskMessage(archiveName, archive, archivedTask);
        } else {
            throw new InvalidCommandDukeException("OOF!! There is no task labelled that number!!");
        }

    }
}
