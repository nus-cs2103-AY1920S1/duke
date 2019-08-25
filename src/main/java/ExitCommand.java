public class ExitCommand extends Command {
    public void execute(TaskList t, Ui ui, Storage storage) {
        ui.showExitMessage();
        storage.save(t.list);
    }
}
