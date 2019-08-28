package duke.commands;

/**
 * Implements the incorrect command.
 * @author Lim Yong Shen, Kevin
 */
public class IncorrectCommand extends Command {

    private static final String DEFAULT_MESSAGE = "\u2639 OOPS!!! I'm sorry,"
    + " but I don't know what that means :-(\n";
    private String errorMessage;
    private boolean hasErrorMessage;

    /**
     * Constructs an IncorrectCommand.
     */
    public IncorrectCommand() {
        hasErrorMessage = false;
    }

    /**
     * Constructs an IncorrectCommand with the specified error message.
     * @param errorMessage The specified error message.
     */
    public IncorrectCommand(String errorMessage) {
        this.errorMessage = errorMessage;
        hasErrorMessage = true;
    }

    /**
     * Executes this IncorrectCommand and returns its CommandResult.
     * @return This IncorrectCommand's CommandResult.
     */
    @Override
    public CommandResult execute() {
        if (hasErrorMessage) {
            return new CommandResult(errorMessage);
        } else {
            return new CommandResult(DEFAULT_MESSAGE);
        }
    }

}
