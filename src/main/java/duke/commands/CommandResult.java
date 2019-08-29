package duke.commands;

/**
 * Represents the result of executing a command.
 * @author Lim Yong Shen, Kevin
 */
public class CommandResult {

    private String result;

    /**
     * Constructs a CommandResult with the specified result.
     * @param result The specified result.
     */
    public CommandResult(String result) {
        this.result = result;
    }

    /**
     * Returns this CommandResult's message.
     * @return This CommandResult's message.
     */
    public String getMessage() {
        return result;
    }

}
