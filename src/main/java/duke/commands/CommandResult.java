package duke.commands;

/**
 * Represents the result of executing a command.
 * @author Lim Yong Shen, Kevin
 */
public class CommandResult {

    private String result;

    /**
     * Constructs a command result with the specified result.
     * @param result The specified result.
     */
    public CommandResult(String result) {
        this.result = result;
    }

    /**
     * Returns this command result's message.
     * @return This command result's message.
     */
    public String getMessage() {
        return result;
    }

}
