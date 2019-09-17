public class ExitCommand extends Command {
    /**
     * Constructor.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Update save file with all changes and display confirmation.
     *
     * @param tasks Class dealing with manipulating global LinkedList storing tasks
     * @param ui User Interface
     * @param storage Class dealing with local storage of tasks
     * @throws DukeException If fails to save file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null : "Tasklist instance is null!";
        assert ui != null : "Ui instance is null!";
        assert  storage != null : "Storage instance is null!";
        storage.updateSaveFile(tasks.getAllTasks());
        return ui.showGoodbye();
    }

}
