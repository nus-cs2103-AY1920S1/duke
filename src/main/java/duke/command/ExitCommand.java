package duke.command;

import duke.task.TaskList;

/**
 * A class representing an exit command.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Returns the result of executing the command.
     * @param tasks a list task to work on.
     * @return the result of executing the command.
     */
    @Override
    public CommandResult execute(TaskList tasks) {
        return new CommandResult(CommandType.Exit);
    }
}
