public class AddCommand extends Command {
    Task task;

    public AddCommand(Task t) {
        this.task = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.task);
        ui.showAddedTask(this.task, tasks.getListSize());
    }
}
