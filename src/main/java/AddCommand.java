public class AddCommand extends Command {
    Task task;

    /**
     * Constructor.
     *
     * @param t Task to be added
     */
    public AddCommand(Task t) {
        this.task = t;
    }

    /**
     * Adds task to TaskList and displays confirmation.
     *
     * @param tasks Class dealing with manipulating global LinkedList storing tasks
     * @param ui User Interface
     * @param storage Class dealing with local storage of tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null : "Tasklist instance is null!";
        assert ui != null : "Ui instance is null!";
        if (!tasks.isDuplicate(this.task)) {
            tasks.addTask(this.task);
            return ui.showAddedTask(this.task, tasks.getListSize());
        } else {
            throw new DukeException("Task is a duplicate!");
        }
    }
}
