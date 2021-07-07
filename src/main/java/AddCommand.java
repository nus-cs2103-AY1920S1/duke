import java.util.HashMap;

/**
 * Represents the command of adding a Task object into a Duke object's TaskList object.
 */
public class AddCommand extends Command {
    protected Task task;

    /**
     * Creates an AddCommand object.
     *
     * @param task Task object to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the adding of the Task object into a Duke object's TaskList object.
     * The add task message is returned.
     *
     * @param tasks TaskList object to add the task to.
     * @param ui Duke object's Ui object to display add task message.
     * @param taskStorage Duke object's Storage object to access file for loading/saving tasks.
     * @param archiveStorage Duke object's ArchiveStorage object to access archives file for loading/saving archives.
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     * @return String add task message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, TaskStorage taskStorage, ArchiveStorage archiveStorage,
                          HashMap<String, TaskList> archives) {
        tasks.addTask(this.task);
        taskStorage.loadTasksToFile(tasks);
        // Precondition for showAddTaskMessage
        assert tasks.taskListSize() > 0 : "List of Tasks should be at least 1 after adding new task";
        return ui.showAddTaskMessage(this.task, tasks.taskListSize());
    }

}
