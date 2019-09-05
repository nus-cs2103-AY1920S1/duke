package duke.command;

/**
 * Represents a {@link Command} to inform the user that he has entered an incorrect command.
 */
public class IncorrectCommand extends Command {

    @Override
    public CommandResult execute() {
        return new CommandResult("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
