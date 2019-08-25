public class AddCommand extends Command {
    public AddCommand(Task task) {
        super.task = task;
    }

    public void execute(TaskList t, Ui ui, Storage storage) {
        t.list.add(task);
        ui.showAddedTask(task, t.list.size());
    }
}