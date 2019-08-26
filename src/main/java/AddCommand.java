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
     * The add task message is displayed.
     *
     * @param tasks TaskList object to add the task to.
     * @param ui Duke object's Ui object to display add task message.
     * @param storage Duke object's Storage object to access file for loading/saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.showAddTaskMessage(this.task, tasks.taskListSize());
    }

    /**
     * Indicates the exit condition of the running Duke object.
     *
     * @return AddCommand is not the ExitCommand so it returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
