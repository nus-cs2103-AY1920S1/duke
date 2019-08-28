public class DoneCommand extends Command {
    public DoneCommand(String taskId) {
        super(taskId);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.get(args);
        task.markAsDone();
        ui.printMarkTaskAsDone(task);
    }
}
