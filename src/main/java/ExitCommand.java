public class ExitCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception{
        storage.loadTasksToFile(tasks);
        ui.showByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
