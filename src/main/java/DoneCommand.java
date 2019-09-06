public class DoneCommand extends Command {
    int completedTaskIndex;

    public DoneCommand(int completedTaskNum) {
        this.completedTaskIndex = completedTaskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task completedTask = taskList.completeTask(completedTaskIndex);
        String taskDescription = completedTask.toString();
        ui.showDoneMessage(taskDescription);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}