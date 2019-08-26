package seedu.duke.cli.commands;

import seedu.duke.cli.CommandException;
import seedu.duke.cli.annotations.Argument;
import seedu.duke.cli.annotations.CommandConstructor;
import seedu.duke.tasks.DeadlineTask;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TodoTask;

import java.time.LocalDateTime;

public class DeadlineCommand extends AddCommand {
    private final String description;
    private final LocalDateTime deadline;

    @CommandConstructor("deadline")
    public DeadlineCommand(@Argument(trailing = true) String description,
                           @Argument(trailing = true, prefix = "/by") LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    protected Task createTask() throws CommandException {
        if (description == null || description.isBlank()) {
            throw new CommandException("Task description cannot be empty.");
        }

        return new DeadlineTask(description, deadline);
    }
}
