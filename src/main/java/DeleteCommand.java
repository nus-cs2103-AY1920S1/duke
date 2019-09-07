public class DeleteCommand extends Command {

    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task taskChanged = tasks.deleteTask(taskNum);
        ui.showDeletedTask(taskChanged);
        ui.showNumTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}