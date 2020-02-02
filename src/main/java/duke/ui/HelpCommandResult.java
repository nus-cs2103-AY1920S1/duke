package duke.ui;

import duke.command.CommandResult;
import duke.command.CommandType;

import java.util.Optional;

public class HelpCommandResult extends CommandResult {
    private Optional<CommandType> helpCommandType;
    private Optional<CommandType.SubCommandType> helpSubCommandType;

    /**
     * Returns the command type asked by the help command.
     * @return the command type asked by the help command.
     */
    public Optional<CommandType> getHelpCommandType() {
        return helpCommandType;
    }

    /**
     * Returns the sub command type asked by the help command.
     * @return the sub command type asked by the help command.
     */
    public Optional<CommandType.SubCommandType> getHelpSubCommandType() {
        return helpSubCommandType;
    }

    /**
     * Constructor specifying the command type and sub command type asked by the help command.
     */
    public HelpCommandResult(Optional<CommandType> commandType, Optional<CommandType.SubCommandType> subCommandType) {
        super(CommandType.Help);
        this.helpCommandType = commandType;
        this.helpSubCommandType = subCommandType;
    }
}
