package duke.command;

import duke.model.Event;
import duke.model.Task;

import java.time.LocalDateTime;

public class AddEventTaskCommand extends AddCommand {
    public AddEventTaskCommand(String command) {
        super(command);
    }

    @Override
    protected Task instantiateTask() {
        final String[] eventArgs = command.split("(?:\\A| )/at\\b ?");
        final String eventDescription = eventArgs[0].stripTrailing();
        if (eventDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of an event cannot be empty.");
        }

        final String eventDateTimeText = eventArgs[1].stripLeading();
        final LocalDateTime eventDateTime = LocalDateTime.parse(eventDateTimeText, DATE_TIME_FORMATTER);

        return new Event(eventDescription, eventDateTime);
    }
}
