public class AddCommand implements Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds task to the list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.save(tasks);

        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("  " + task);
        printTotalTask(tasks, ui);
    }

    private void printTotalTask(TaskList tasks, Ui ui) {
        boolean isPlural = tasks.size() > 1;
        ui.showMessage("Now you have " + tasks.size() + " task" + (isPlural ? "s" : "") + " in the list.");
    }
}
