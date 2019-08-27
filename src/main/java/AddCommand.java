public class AddCommand extends Command {
    protected final Task task;

    AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        taskList.add(task);
        storage.update(taskList);
        ui.showAddMessage(taskList.size(), task);
    }
}
