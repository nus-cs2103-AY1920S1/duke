import java.util.HashMap;

/**
 * Represents the command of deleting a Task object from a Duke object's TaskList object.
 */
public class DeleteCommand extends Command {
    protected int taskNumber;

    /**
     * Creates a DeleteCommand object.
     *
     * @param taskNumber Index of the Task object to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the deletion of the Task object from a Duke object's TaskList object.
     * The given index of task to be deleted is first checked to be valid or not.
     * After deletion, the delete task message is returned.
     *
     * @param tasks TaskList object that contains task to be deleted.
     * @param ui Duke object's Ui object to display delete task message.
     * @param taskStorage Duke object's Storage object to access file for loading/saving tasks.
     * @param archiveStorage Duke object's ArchiveStorage object to access archives file for loading/saving archives.
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     * @return String delete task message.
     * @throws InvalidCommandDukeException Thrown when an invalid task number index is given so no Task
     *     object can be retrieved from the TaskList object.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, TaskStorage taskStorage, ArchiveStorage archiveStorage,
                          HashMap<String, TaskList> archives) throws InvalidCommandDukeException {
        if (taskNumber >= 0 && taskNumber < tasks.taskListSize()) {
            Task removedTask = tasks.getTask(taskNumber);
            tasks.deleteTask(taskNumber);
            taskStorage.loadTasksToFile(tasks);
            return ui.showDeleteTaskMessage(removedTask, tasks.taskListSize());
        } else {
            throw new InvalidCommandDukeException("OOF!!! There is no task labelled that number!!");
        }

    }
}
