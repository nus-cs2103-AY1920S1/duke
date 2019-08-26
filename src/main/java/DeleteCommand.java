public class DeleteCommand extends Command {
    private int index;

    DeleteCommand(int index) {
        super();
        this.index = index;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.getListSize()) {
            throw new DukeException("OOPS!!! Task not found.");
        }
        Task task = tasks.getTask(index);
        tasks.deleteTask(task);
        try {
            storage.writeToHardDisk(tasks);
        } catch (DukeException ex) {
            ui.printException(ex);
        }

        ui.printDeleteMessage(task, tasks);
}

    public boolean isExit() {
        return false;
    }
}
