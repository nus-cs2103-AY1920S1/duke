package duke.command;

import duke.task.TaskList;

/**
 * This is the class for the command to execute the LIST command.
 * lists down all existing values in taskList
 */
public class ListCommand extends Command {
    private static final Commands LIST_COMMAND_TYPE = Commands.LIST;

    /**
     * Returns the command type of the command.
     * @return Command type
     */
    public Commands getCommandType() {
        return LIST_COMMAND_TYPE;
    }

    @Override
    public String execute(TaskList tasklist) {
        return tasklist.getPrintListMessage();
    }
}
