package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand object.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the command, listing all tasks in the task list.
     *
     * @param duke Chat bot.
     * @param storage  Data file of chat bot.
     * @param taskList  Task list of chat bot.
     * @return Result of command.
     */
    @Override
    public String execute(Duke duke, Storage storage, TaskList taskList) {
        return Ui.showTaskList(taskList);
    }
}
