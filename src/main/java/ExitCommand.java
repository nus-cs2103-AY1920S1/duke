public class ExitCommand extends Command {
    public ExitCommand() {
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveList(tasks);
        ui.sayGoodbye();
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
