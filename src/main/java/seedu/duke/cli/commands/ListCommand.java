package seedu.duke.cli.commands;

import seedu.duke.tasks.Task;
import seedu.duke.cli.Command;
import seedu.duke.cli.annotations.CommandConstructor;

import java.util.List;

public class ListCommand implements Command {
    @CommandConstructor("list")
    public ListCommand() {}

    @Override
    public boolean execute(List<Task> taskList) {
        for (int i = 0; i < taskList.size(); ++i) {
            System.out.printf("%d. %s%n", i + 1, taskList.get(i));
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj instanceof ListCommand;
    }
}
