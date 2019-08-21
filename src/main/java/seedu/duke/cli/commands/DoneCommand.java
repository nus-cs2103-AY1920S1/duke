package seedu.duke.cli.commands;

import seedu.duke.tasks.Task;
import seedu.duke.cli.Command;
import seedu.duke.cli.CommandException;
import seedu.duke.cli.annotations.CommandConstructor;

import java.util.List;

public class DoneCommand implements Command {
    private final int id;

    @CommandConstructor("done")
    public DoneCommand(int id) {
        this.id = id;
    }

    @Override
    public boolean execute(List<Task> taskList) throws CommandException {
        if (id < 1 || id > taskList.size()) {
            throw new CommandException("Invalid task ID");
        }

        Task t = taskList.get(id - 1);
        t.setDone(true);
        System.out.printf("Nice! I've marked this task as done:%n%s%n", t);
        return true;
    }
}
