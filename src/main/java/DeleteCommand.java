public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Deletes the index-th task from list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(index);
        storage.save(tasks);
    
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage("  " + task);
        printTotalTask(tasks, ui);
    }

    private void printTotalTask(TaskList tasks, Ui ui) {
        boolean isPlural = tasks.size() > 1;
        ui.showMessage("Now you have " + tasks.size() + " task" + (isPlural ? "s" : "") + " in the list.");
    }
}
