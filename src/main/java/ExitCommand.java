public class ExitCommand extends Command {
    /**
     * Constructor
     */
    public ExitCommand() {
        super();
    }

    /**
     * Update save file with all changes and display confirmation
     *
     * @param tasks Class dealing with manipulating global LinkedList storing tasks
     * @param ui User Interface
     * @param storage Class dealing with local storage of tasks
     * @throws DukeException If fails to save file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.updateSaveFile(tasks.getAllTasks());
        ui.showGoodbye();
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
