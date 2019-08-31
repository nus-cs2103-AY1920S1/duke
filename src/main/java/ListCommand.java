public class ListCommand extends Command {
    /**
     * Constructor
     */
    public ListCommand() {
        super();
    }

    /**
     * Display all tasks
     *
     * @param tasks Class dealing with manipulating global LinkedList storing tasks
     * @param ui User Interface
     * @param storage Class dealing with local storage of tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks.getAllTasks());
    }
}
