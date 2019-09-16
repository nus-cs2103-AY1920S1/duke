package duke.command;

import duke.model.Deadline;
import duke.model.Task;

import java.time.LocalDateTime;

public class AddDeadlineTaskCommand extends AddCommand {
    public AddDeadlineTaskCommand(String command) {
        super(command);
    }

    @Override
    protected Task instantiateTask() {
        final String[] deadlineArgs = command.split("(?:\\A| )/by\\b ?");
        final String deadlineDescription = deadlineArgs[0].stripTrailing();
        if (deadlineDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of a deadline cannot be empty.");
        }

        final String deadlineDueText = deadlineArgs[1].stripLeading();
        final LocalDateTime deadlineDue = LocalDateTime.parse(deadlineDueText, DATE_TIME_FORMATTER);

        return new Deadline(deadlineDescription, deadlineDue);
    }
}
