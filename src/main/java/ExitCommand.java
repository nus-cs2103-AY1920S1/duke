public class ExitCommand extends Command {
    public ExitCommand() {

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
        storage.saveTasks(taskList.getTaskList());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}