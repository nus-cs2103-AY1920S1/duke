public class AddCommand extends Command{
    Task taskToAdd;

    /**
     * Class constructor.
     * @param taskToAdd Task to be Added
     */
    public AddCommand(Task taskToAdd) {
        super(false);
        this.taskToAdd = taskToAdd;
    }

    /**
     * Execute add command.
     * @param storage Storage used to save tasks into local storage
     * @param tasks TaskList used to store tasks
     * @param ui UI used to interact
     */
    @Override
    public void execute(Storage storage, TaskList tasks, UI ui) {
        tasks.addTask(taskToAdd);
        storage.appendTaskToFile(taskToAdd);
        ui.echoAddedTask(taskToAdd, tasks.getSize());
    }
}
