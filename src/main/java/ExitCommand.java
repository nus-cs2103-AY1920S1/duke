public class ExitCommand extends Command {
    public void execute(TaskList t, Ui ui, Storage storage) {
        ui.showExitMessage(t.list);
        storage.save(t.list);
    }
}
