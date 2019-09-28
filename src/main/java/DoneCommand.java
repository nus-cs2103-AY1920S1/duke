public class DoneCommand extends Command {

    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int taskIndex = Integer.parseInt(fullCommand.replaceAll("\\D+","")) - 1;
        Task completedTask = tasks.getTasks().get(taskIndex);
        tasks.doneTask(completedTask);
        ui.showTaskDone(completedTask);
    }

    public boolean isExit() {
        return false;
    }
}
