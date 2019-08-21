package seedu.duke.cli.commands;

import seedu.duke.Task;
import seedu.duke.cli.Command;
import seedu.duke.cli.annotations.CommandConstructor;

import java.util.List;

public class ListCommand implements Command {
    @CommandConstructor("list")
    public ListCommand() {}

    @Override
    public boolean execute(List<Task> taskList) {
        return false;
    }
}
