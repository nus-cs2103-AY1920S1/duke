package seedu.duke.cli.commands;

import seedu.duke.cli.Command;
import seedu.duke.cli.CommandException;
import seedu.duke.cli.annotations.CommandConstructor;
import seedu.duke.tasks.Task;

import java.util.List;
import java.util.Objects;

public class DeleteCommand implements Command {
    private final int id;

    @CommandConstructor("delete")
    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public boolean execute(List<Task> taskList) throws CommandException {
        if (id < 1 || id > taskList.size()) {
            throw new CommandException("Invalid task ID");
        }

        Task t = taskList.remove(id - 1);
        System.out.printf(
                "Noted. I've removed this task:%n  %s%nNow you have %d tasks in the list.%n",
                t, taskList.size());
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

        DeleteCommand that = (DeleteCommand) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
