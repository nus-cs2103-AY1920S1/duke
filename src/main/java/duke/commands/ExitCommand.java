package duke.commands;

/**
 * Implements the exit command.
 * @author Lim Yong Shen, Kevin
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Executes this exit command and returns its command result.
     * @return This exit command's command result.
     */
    @Override
    public CommandResult execute() {
        String exitMessage = "Bye. Hope to see you again soon!\nYou may close the application now.\n";
        return new CommandResult(exitMessage);
    }

}
