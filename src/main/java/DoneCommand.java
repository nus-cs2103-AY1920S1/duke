public class DoneCommand extends Command {
    int id;

    /**
     * Constructor.
     *
     * @param i Index of task to be done
     */
    public DoneCommand(int i) {
        this.id = i;
    }

    /**
     * Marks task in Tasklist as complete and displays confirmation.
     *
     * @param tasks Class dealing with manipulating global LinkedList storing tasks
     * @param ui User Interface
     * @param storage Class dealing with local storage of tasks
     * @throws DukeException If task does not exist
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null : "Tasklist instance is null!";
        assert ui != null : "Ui instance is null!";
        tasks.completeTask(this.id);
        return ui.showComplete(tasks.getTask(this.id));
    }
}
