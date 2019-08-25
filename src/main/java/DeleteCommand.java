public class DeleteCommand extends Command {
    int id;
    public DeleteCommand(int i) {
        this.id = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.getTask(id);
        tasks.deleteTask(this.id);
        ui.showDelete(t, tasks.getListSize());
    }
}
