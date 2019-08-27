/**
 * AddCommands represent the addition of Tasks to the TaskList.
 */
public class AddCommand extends Command {
    private Task userTask;
    public AddCommand(Task userTask) {
        super();
        isExit = false;
        this.userTask = userTask;
    }

    /**
     * Adds a task to the TaskList, displays messages to user, and saves tasks to the hard disk
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(userTask);
        Integer numberOfTasks = tasks.getNumberOfTasks();

        ui.showAddMessage();
        ui.showTask(userTask);
        ui.showNumberOfTasks(numberOfTasks);

        storage.writeTaskToFile(tasks);
    }
}