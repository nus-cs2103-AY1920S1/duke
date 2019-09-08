package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(null, null);
    }
    @Override
    public String execute() {
        return "Bye...";
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
