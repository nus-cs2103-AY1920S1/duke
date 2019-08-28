public class AddCommand extends Command {
    public AddCommand(Task task) {
        super(task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        ui.printAddTask(task, tasks.size());
    }
}
