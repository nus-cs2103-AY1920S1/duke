public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }

    protected void persistState(TaskList tasks, Storage storage) throws DukeException {
        storage.saveDataToFile(tasks.getAllTasks());
    }
}
