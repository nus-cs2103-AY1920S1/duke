package seedu.duke.command;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
