public class AddCommand extends Command{
    Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        super(false);
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, UI ui) {
        tasks.addTask(taskToAdd);
        storage.appendToFile(taskToAdd.serialise());
        ui.echoAddedTask(taskToAdd.toString(), tasks.getSize());
    }

}
