package duke.commands;

/**
 * Implements the exit command.
 * @author Lim Yong Shen, Kevin
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Executes this ExitCommand and returns its CommandResult.
     * @return This ExitCommand's CommandResult.
     */
    @Override
    public CommandResult execute() {
        String exitMessage = "Bye. Hope to see you again soon!\n";
        return new CommandResult(exitMessage);
    }

    /**
     * Returns true since this is an ExitCommand.
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
