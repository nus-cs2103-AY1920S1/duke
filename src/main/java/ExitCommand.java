public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
        ui.closeScanner();
    }

    public boolean isExit() {
        return true;
    }
}


