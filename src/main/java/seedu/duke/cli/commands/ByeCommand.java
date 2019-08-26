package seedu.duke.cli.commands;

import seedu.duke.cli.Command;
import seedu.duke.cli.annotations.CommandConstructor;
import seedu.duke.tasks.Task;

import java.util.List;

public class ByeCommand implements Command {
    @CommandConstructor("bye")
    public ByeCommand() {
    }

    @Override
    public boolean execute(List<Task> taskList) {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj instanceof ByeCommand;
    }
}
