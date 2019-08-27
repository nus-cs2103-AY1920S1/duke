public class DeleteCommand extends Command {
    protected final int index;

    DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        Task task = taskList.remove(index);
        storage.update(taskList);
        ui.showDeleteMessage(taskList.size(), task);
    }
}
