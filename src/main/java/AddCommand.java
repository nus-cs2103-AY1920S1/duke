public abstract class AddCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
    {
        taskList.addTask();
        ui.showAddedTask("", taskList.getTasks());
    }
}
