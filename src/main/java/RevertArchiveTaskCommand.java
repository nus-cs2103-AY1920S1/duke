import java.util.HashMap;

/**
 * Represents the command of reverting an archived task back to the Duke object's list of tasks.
 */
public class RevertArchiveTaskCommand extends ArchiveCommand {
    private int taskNumber;
    private String archiveName;

    /**
     * Creates a RevertArchiveTaskCommand object.
     *
     * @param taskNumber Index of task in archive to be reverted.
     * @param archiveName Name of archive to revert task from.
     */
    public RevertArchiveTaskCommand(int taskNumber, String archiveName) {
        this.taskNumber = taskNumber;
        this.archiveName = archiveName;
    }

    /**
     * Executes the reverting of a task from an archive back to the list of tasks.
     * Returns the revert archive task message.
     *
     * @param tasks TaskList object to add the task to.
     * @param ui Duke object's Ui object to display add task message.
     * @param taskStorage Duke object's TaskStorage object to access tasks file for loading/saving normal tasks.
     * @param archiveStorage Duke object's ArchiveStorage object to access archives file for loading/saving archives.
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     * @return String revert archive task message.
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
            Task revertedTask = archive.getTask(taskNumber);
            archive.deleteTask(taskNumber);
            tasks.addTask(revertedTask);
            taskStorage.loadTasksToFile(tasks);
            archiveStorage.loadArchivedTasksToFile(archives);
            return ui.showRevertArchiveTaskMessage(archiveName, revertedTask);
        } else {
            throw new InvalidCommandDukeException("OOF!! There is no task labelled that number!!");
        }
    }
}
