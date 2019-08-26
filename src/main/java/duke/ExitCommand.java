package duke;

public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
        ui.closeScanner();
    }

    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ExitCommand;
    }
}
