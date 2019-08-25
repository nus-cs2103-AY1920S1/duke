public class AddCommand extends Command {
    Task task;

    /**
     * Constructor
     *
     * @param t Task to be added
     */
    public AddCommand(Task t) {
        this.task = t;
    }

    /**
     * Adds task to TaskList and displays confirmation
     *
     * @param tasks Class dealing with manipulating global LinkedList storing tasks
     * @param ui User Interface
     * @param storage Class dealing with local storage of tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.showAddedTask(this.task, tasks.getListSize());
    }
}
