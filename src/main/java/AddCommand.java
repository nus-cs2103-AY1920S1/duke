public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.add(task);
            ui.showSuccessMessage("adding", task);
            storage.store(tasks);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
