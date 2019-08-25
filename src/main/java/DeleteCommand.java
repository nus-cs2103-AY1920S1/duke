public class DeleteCommand extends Command {
    int id;

    /**
     * Constructor
     *
     * @param i Index of task to be deleted
     */
    public DeleteCommand(int i) {
        this.id = i;
    }

    /**
     * Deletes task from TaskList and displays confirmation
     *
     * @param tasks Class dealing with manipulating global LinkedList storing tasks
     * @param ui User Interface
     * @param storage Class dealing with local storage of tasks
     * @throws DukeException If task does not exist
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.getTask(id);
        tasks.deleteTask(this.id);
        ui.showDelete(t, tasks.getListSize());
    }
}
