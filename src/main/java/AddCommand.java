public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeException {
        taskList.addTask(task);
        storage.save(task.getSimplifiedRepresentation());
        ui.showAddition(task);
        ui.showTaskSize(taskList);
    }
}
