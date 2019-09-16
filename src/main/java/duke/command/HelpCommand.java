package duke.command;

import duke.exception.IllegalDescriptionException;
import duke.exception.IllegalIndexOfTaskException;
import duke.task.TaskList;
import duke.ui.HelpCommandResult;

import java.util.Optional;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    private Optional<CommandType> commandType = Optional.empty();
    private Optional<CommandType.SubCommandType> subCommandType = Optional.empty();

    /**
     * Constructor.
     */
    public HelpCommand() {
    }

    /**
     * Constructor specifying the command type asked by the help command.
     */
    public HelpCommand(CommandType commandType) {
        this.commandType = Optional.of(commandType);
    }

    /**
     * Constructor specifying the sub command type asked by the help command.
     */
    public HelpCommand(CommandType.SubCommandType subCommandType) {
        this.subCommandType = Optional.of(subCommandType);
    }

    /**
     * Constructor specifying the command type and sub command type asked by the help command.
     */
    public HelpCommand(CommandType commandType, CommandType.SubCommandType subCommandType) {
        this.commandType = Optional.of(commandType);
        this.subCommandType = Optional.of(subCommandType);
    }

    /**
     * Returns the result of executing the command.
     * Add the task to the list.
     * @param tasks a list task to work on.
     * @return the result of executing the command.
     */
    @Override
    public CommandResult execute(TaskList tasks) throws IllegalIndexOfTaskException, IllegalDescriptionException {
        return new HelpCommandResult(commandType, subCommandType);
    }
}
