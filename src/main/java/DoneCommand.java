public class DoneCommand extends Command {
    protected final int index;

    DoneCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        Task task = taskList.get(index);
        task.markAsDone();
        storage.update(taskList);
        ui.showDoneMessage(task);
    }
}
