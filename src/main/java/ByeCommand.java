public class ByeCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
        ui.closeScanner();
    }
    public boolean isExit() {
        return true;
    }
}