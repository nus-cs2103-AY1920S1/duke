package seedu.duke.cli.commands;

import seedu.duke.cli.CommandException;
import seedu.duke.cli.annotations.Argument;
import seedu.duke.cli.annotations.CommandConstructor;
import seedu.duke.tasks.DeadlineTask;
import seedu.duke.tasks.EventTask;
import seedu.duke.tasks.Task;

public class EventCommand extends AddCommand {
    private final String description;
    private final String time;

    @CommandConstructor("event")
    public EventCommand(@Argument(trailing = true) String description,
                        @Argument(trailing = true, prefix = "/at") String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    protected Task createTask() throws CommandException {
        if (description == null || description.isBlank()) {
            throw new CommandException("Task description cannot be empty.");
        }

        return new EventTask(description, time);
    }
}
