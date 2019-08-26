package seedu.duke.cli.commands;

import seedu.duke.cli.Command;
import seedu.duke.cli.CommandException;
import seedu.duke.cli.annotations.CommandConstructor;
import seedu.duke.tasks.Task;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DoneCommand that = (DoneCommand) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
