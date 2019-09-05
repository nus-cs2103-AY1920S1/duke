package duke.command;

import static java.util.Objects.requireNonNull;

/**
 * Represents a {@link Command} to inform the user that he has entered an incorrect command.
 */
public class IncorrectCommand extends Command {

    private final String message;

    public IncorrectCommand(String message) {
        this.message = requireNonNull(message);
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(message);
    }
}
