import java.time.LocalDateTime;

class AddDeadlineTaskCommand extends AddCommand {
    public AddDeadlineTaskCommand(String command) {
        super(command);
    }

    protected Task instantiateTask() {
        final String[] deadlineArgs = command.split(" /by ");
        final String deadlineDescription = deadlineArgs[0];
        if (deadlineDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of a deadline cannot be empty.");
        }

        final LocalDateTime deadlineDue = LocalDateTime.parse(deadlineArgs[1], dateTimeFormatter);

        return new Deadline(deadlineDescription, deadlineDue);
    }
}
