package command;

import core.Storage;
import exception.DukeIllegalArgumentException;
import ui.Ui;
import task.TaskList;

/**
 * ListTaskCommand class.
 *
 * <p>Command to show all tasks in list.
 *
 * @author Marcus Ong
 */
public class ListTasksCommand extends Command {

    public ListTasksCommand(String commandString) {
        super(CommandType.LIST_TASKS, commandString);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }
}
