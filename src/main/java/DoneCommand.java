public class DoneCommand implements Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Marks the index-th task to be done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markAsDone(index);
        storage.save(tasks);

        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage("  " + tasks.get(index));
    }
}
