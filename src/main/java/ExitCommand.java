public class ExitCommand extends Command {
    public ExitCommand() {
        super();
        isExit = true;
    }

    public void execute (TaskList task, Ui ui, Storage storage) {
        storage.writeToFile(task);
        ui.showExitMessage();
    }
}
