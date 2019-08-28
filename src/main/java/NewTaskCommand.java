public abstract class NewTaskCommand extends Command {
    private Task task;

    public NewTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute() throws DukeException {
        this.taskList.add(this.task);
    }
}
