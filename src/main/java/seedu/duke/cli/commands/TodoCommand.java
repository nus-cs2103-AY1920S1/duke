package seedu.duke.cli.commands;

import seedu.duke.cli.CommandException;
import seedu.duke.cli.annotations.Argument;
import seedu.duke.cli.annotations.CommandConstructor;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TodoTask;

public class TodoCommand extends AddCommand {
    private final String description;

    @CommandConstructor("todo")
    public TodoCommand(@Argument(trailing = true) String description) {
        this.description = description;
    }

    @Override
    protected Task createTask() throws CommandException {
        if (description == null || description.isBlank()) {
            throw new CommandException("Task description cannot be empty.");
        }

        return new TodoTask(description);
    }
}
