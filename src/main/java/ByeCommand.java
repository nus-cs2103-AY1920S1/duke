public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    public void execute(TaskList tasks, DukeUI ui, StorageData storage) {
        ui.printByeMessage();
        System.exit(0);
    }
}
