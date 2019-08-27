public class AddCommand extends Command {
    private Task userTask;

    public AddCommand(Task userTask) {
        super();
        isExit = false;
        this.userTask = userTask;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(userTask);
        Integer numberOfTasks = tasks.getNumberOfTasks();

        ui.showAddMessage();
        ui.showTask(userTask);
        ui.showNumberOfTasks(numberOfTasks);

        storage.writeTaskToFile(tasks);
    }
}