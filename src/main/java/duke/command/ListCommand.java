package duke.command;

import duke.task.TaskList;

public class ListCommand extends Command {
    private static final Commands LIST_COMMAND_TYPE = Commands.LIST;

    /**
     * @return Command type of command
     */
    public Commands getCommandType() {
        return LIST_COMMAND_TYPE;
    }

    @Override
    public String execute(TaskList tasklist) {
        return tasklist.getPrintListMessage();
    }
}
