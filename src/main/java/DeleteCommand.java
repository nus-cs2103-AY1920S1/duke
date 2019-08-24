public class DeleteCommand extends Command {

    private int target;

    DeleteCommand(int target) {
        this.target = target;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task removedTask = taskList.removeTaskAt(target);
        ui.showTaskDeleted(taskList.getTotalTask(), removedTask);
    }
}
