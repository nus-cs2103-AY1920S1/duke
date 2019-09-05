package duke.command;

/**
 * Represents a {@link Command} to shut down the {@link duke.Duke} application.
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public CommandResult execute() {
        isExit = true;
        return new CommandResult("Bye. Hope to see you again soon!");
    }
}
