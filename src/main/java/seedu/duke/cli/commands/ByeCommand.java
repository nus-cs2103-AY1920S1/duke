package seedu.duke.cli.commands;

import seedu.duke.tasks.Task;
import seedu.duke.cli.Command;
import seedu.duke.cli.annotations.CommandConstructor;

import java.util.List;

public class ByeCommand implements Command {
    @CommandConstructor("bye")
    public ByeCommand() {}

    @Override
    public boolean execute(List<Task> taskList) {
        return false;
    }
}
