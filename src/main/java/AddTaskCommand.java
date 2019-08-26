public class AddTaskCommand extends Command {
    private Task task;

    public AddTaskCommand(String commandString, Task task) {
        super(commandString);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIOException{
        tasks.add(this.task);
        ui.replyAddTask(task, tasks.size());
        storage.save(tasks);
    }
}
