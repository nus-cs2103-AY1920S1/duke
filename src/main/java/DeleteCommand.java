public class DeleteCommand extends Command {

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int taskIndex = Integer.parseInt(fullCommand.replaceAll("\\D+","")) - 1;
        Task deletedTask = tasks.getTasks().get(taskIndex);
        tasks.deleteTask(taskIndex);
        ui.showTaskDeleted(deletedTask, tasks.getNumberOfTasks());
    }

    public boolean isExit() {
        return false;
    }
}
