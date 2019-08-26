public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
        storage.updateData(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
