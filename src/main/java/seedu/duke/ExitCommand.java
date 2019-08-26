package seedu.duke;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    public void execute(TaskList list, Ui ui, Storage storage) {
        exit = true;
        ui.exit();
    }

}
