package duke.command;

import duke.task.TaskList;

/**
 * A class representing a list command.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Class constructor.
     */
    public ListCommand(){
    }

    /**
     * Executes the command.
     * @param tasks a list task to work on.
     * @return
     */
    @Override
    public CommandResult execute(TaskList tasks) {
        return new CommandResult(CommandType.List, tasks.getTasks());
    }
}