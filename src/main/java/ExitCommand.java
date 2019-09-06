public class ExitCommand implements Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
