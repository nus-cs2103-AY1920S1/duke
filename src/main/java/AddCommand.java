public class AddCommand extends Command {
    Task newTask;

    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(newTask);
        String taskDescription = newTask.toString();
        ui.showAddTaskMessage(taskDescription, taskList.getListSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}