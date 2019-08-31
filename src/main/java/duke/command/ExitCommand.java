package duke.command;

import duke.exception.IllegalIndexOfTaskException;
import duke.task.TaskList;

public class ExitCommand extends Command{
    public static final String COMMAND_WORD = "bye";

    @Override
    public CommandResult execute(TaskList tasks) throws IllegalIndexOfTaskException {
        return new CommandResult(CommandType.Exit);
    }
}
