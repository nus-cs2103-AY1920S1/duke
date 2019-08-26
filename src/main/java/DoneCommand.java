public class DoneCommand extends Command {
    private int index;

    DoneCommand(int index) {
        super();
        this.index = index;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.getListSize()) {
            throw new DukeException("OOPS!!! Task not found.");
        }
        Task task = tasks.getTask(index);
        task.markDone();
        try {
            storage.writeToHardDisk(tasks);
        } catch (DukeException ex) {
            ui.printException(ex);
        }

        ui.printDoneMessage(task);
    }

    public boolean isExit() {
        return false;
    }
}
