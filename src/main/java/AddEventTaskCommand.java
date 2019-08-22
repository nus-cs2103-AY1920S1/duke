import java.time.LocalDateTime;

class AddEventTaskCommand extends AddCommand {
    public AddEventTaskCommand(String command) {
        super(command);
    }

    protected Task instantiateTask() {
        final String[] eventArgs = command.split(" /at ");
        final String eventDescription = eventArgs[0];
        if (eventDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of an event cannot be empty.");
        }

        final LocalDateTime eventDateTime = LocalDateTime.parse(eventArgs[1], dateTimeFormatter);

        return new Event(eventDescription, eventDateTime);
    }
}
