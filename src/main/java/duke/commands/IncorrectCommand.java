package duke.commands;

/**
 * Implements the incorrect command, which handles invalid user input.
 * @author Lim Yong Shen, Kevin
 */
public class IncorrectCommand extends Command {

    private static final String DEFAULT_MESSAGE = "OOPS!!! I'm sorry,"
        + " but I don't know what that means :-(\n"
        + "Enter \"help\" (without \"\") for a list of commands.";
    private String errorMessage;
    private boolean hasErrorMessage;

    /**
     * Constructs an incorrect command.
     */
    public IncorrectCommand() {
        hasErrorMessage = false;
    }

    /**
     * Constructs an incorrect command with the specified error message.
     * @param errorMessage The specified error message.
     */
    public IncorrectCommand(String errorMessage) {
        this.errorMessage = errorMessage;
        hasErrorMessage = true;
    }

    /**
     * Executes this incorrect command and returns its command result.
     * @return This incorrect command's command result.
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
