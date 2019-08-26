public class DeleteTaskCommand extends Command {
    private int taskIndex;

    public DeleteTaskCommand(String commandString, int taskIndex) {
        super(commandString);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException, DukeIOException {
        Task task = tasks.delete(taskIndex);
        ui.replyDeleteTask(task, tasks.size());
        storage.save(tasks);
    }
}
