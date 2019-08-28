public class DeleteCommand extends Command {
    public DeleteCommand(String taskId) {
        super(taskId);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.remove(args);
        ui.printDeleteTask(task);
    }
}
